<template>
<div class='book'>
	<div id='page-0' class='page no-anim flipped'>
    	<div class='side-2' id='p0	'>      
			<div class='content'>
			</div>
		</div>
	</div>
  	<div id='page-1' class='page no-anim flipped'>    
    	<div class='side-1' id='p1'>
      		<div class='content'>
			</div>
		</div>    
    	<div class='side-2' id='p2'>      
			<div class='content'>
				<Intro />
			</div>
		</div>
	</div>

  	<div id='page-2' class='page no-anim'>    
    	<div class='side-1' id='p3'>
      		<div class='content'>
				<Timeline />
    		</div>
    	</div>
    	<div class='side-2' id='p4'>      
      		<div class='content'>
				<Calendar v-show="this.getVisibleCalendar" />
				<Detail v-show="this.getVisibleDetail" />
				<Write1 v-show="this.getVisibleWrite" />
      		</div>
    	</div>
	</div>
  
  	<div id='page-3' class='page no-anim'>    
    	<div class='side-1' id='p5'>
      		<div class='content'>
        		<List v-if="this.getVisibleCalendar" />
				<Comment v-show="this.getVisibleDetail" />
				<Write2 v-show="this.getVisibleWrite" />
			</div>
    	</div>
    	<div class='side-2' id='p6'>      
      		<div class='content'>
        		<h2>사진첩1</h2>
    		</div>
    	</div>
  	</div>
  
  	<div id='page-4' class='page no-anim'>    
    	<div class='side-1' id='p7'>
      		<div class='content'>
        		<h2>사진첩2</h2>
			</div>
		</div>  
		<div class='side-2' id='p8'>      
			<div class='content'>
				<Album1 />
			</div>
		</div>
  	</div>
	
	<div id='page-5' class='page no-anim'>    
    	<div class='side-1' id='p9'>
      		<div class='content'>
        		<Video1 />
			</div>
		</div>    
  	</div>
</div>
</template>

<script>
import { mapGetters } from 'vuex'
import Calendar from '@/components/calendar.vue'
import List from '@/components/list.vue'
import Detail from '@/components/detail.vue'
import Comment from '@/components/comment.vue'
import Write1 from '@/components/write1.vue'
import Write2 from '@/components/write2.vue'
import Timeline from '@/components/timeline.vue'
import Intro from '@/components/intro.vue'
import Album1 from '@/components/album/selectDate.vue'
import Video1 from '@/components/video/selectDate.vue'
export default {
	components: {
		Calendar,
		List,
		Detail,
		Comment,
		Write1,
		Write2,
		Timeline,
		Intro,
		Album1,
		Video1,
	},
	computed: {
        ...mapGetters([
            'getChuckList',
            'getSelectedDiary',
			'getVisibleCalendar',
			'getVisibleDetail',
			'getVisibleWrite',
		]),
	},

    mounted() {
		$('.page > div').click(function(e) {
			e.stopPropagation();
		});
        $('.page').click(function() {
			var num = this.id
			num = num.substring(5,6);
			if( $(this).hasClass('flipped') ){
				for(var i=num*1+1; i<6; i++){
					$('#page-'+i).addClass('no-anim').removeClass('flipped');
				}
			}
			else {
				for(var i=0; i<=num; i++){
					$('#page-'+i).removeClass('no-anim').addClass('flipped');
				}
			}
       		reorder()
        });
        function reorder(){
          	$(".book").each(function(){
				var pages=$(this).find(".page")
              	var pages_flipped=$(this).find(".flipped")
              	pages.each(function(i){
					$(this).css("z-index",pages.length-i)
              	})
              	pages_flipped.each(function(i){
					$(this).css("z-index",i+1)
              	})
          	});    
        }
        reorder()
    }
}
</script>

<style scoped>
*, *:before, *:after {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	position: relative;
}

::-webkit-scrollbar {width:0px}
::-webkit-scrollbar-thumb {background: #222;}
::-webkit-scrollbar-track {background: transparent}

::selection {
  background: #222;
  color: white;
  }
::-moz-selection {
  background: #222;
  color: white;
  }

* {
	transform-style: preserve-3d;
}

h1, p {
	margin: 5% 0;
}

.book {
	height: 800px;
	/* max-height: 1200px;
	min-height: 300px; */
	width: 1420px;
	background: url('../assets/layout1.png');
	background-size: cover;
	position: absolute;
	top: 0; left: 0; bottom: 0; right: 0;
	margin: auto;
	perspective: 2500px;
	/* border: 3px solid hsla(30,10%,20%,1); */
	/* border-radius: 5px; */
	/* box-shadow: 0 0 150px hsla(170,0%,0%,.4); */
}

.page {
	height: 717px;
	width: 594px;
	line-height: 1.5;
	border-right: 10px solid transparent;
	position: absolute;
	top: 49px;
	right: 99px;
	transform-origin: 0 50%;
	transition: .8s;
	/*animation: unflip .3s linear;*/
}
[class*='side'] {
	height: 100%;
	width: 100%;
	position: absolute;
	background-color: hsl(30,40%,70%);
	backface-visibility: hidden;
	overflow: auto;
}

.side-1 {
    z-index: 2;
    /* box-shadow: inset 50px 0 50px rgba(0,0,0,.5); */
    transition: .5s;
    background-image: url('../assets/rightpage.png');
    background-size: cover;
}
.side-2 {
    transform: rotateY(180deg);
    /* box-shadow: inset -50px 0 50px rgba(0,0,0,.5); */
    background-image: url('../assets/leftpage.png');
    background-size: cover;
}
.flipped > .side-1 {
    /* box-shadow: inset 300px 0 50px rgba(0,0,0,.8);    */
    transition: .6s;
}
.flipped > [class*='side'] {
    pointer-events: auto;
}
#page-1:after {
	width: 50px;
	height: 100px;
	background: url('../assets/1.png');
	background-size: cover;
	position: absolute;
	top: 0; bottom: 600px; right: -53px;
	margin: auto;
	color: white;
	text-shadow: 0 -1px 0 #222;
	line-height: 50px;
	text-align: center;
	font-family: monospace;
	content: '';
	/* animation: hide .8s linear; */
}
#page-2:after {
	width: 50px;
	height: 100px;
	background: url('../assets/2.png');
	background-size: cover;
	position: absolute;
	top: 0; bottom: 400px; right: -53px;
	margin: auto;
	color: white;
	text-shadow: 0 -1px 0 #222;
	line-height: 50px;
	text-align: center;
	font-family: monospace;
	content: '';
	/* animation: hide .8s linear; */
}
#page-3:after {
	width: 50px;
	height: 100px;
	background: url('../assets/3.png');
	background-size: cover;
	position: absolute;
	top: 0; bottom: 200px; right: -53px;
	margin: auto;
	color: white;
	text-shadow: 0 -1px 0 #222;
	line-height: 50px;
	text-align: center;
	font-family: monospace;
	content: '';
	/* animation: hide .8s linear; */
}
#page-4:after {
	width: 50px;
	height: 100px;
	background: url('../assets/4.png');
	background-size: cover;
	position: absolute;
	top: 0; bottom: 0px; right: -53px;
	margin: auto;
	color: white;
	text-shadow: 0 -1px 0 #222;
	line-height: 50px;
	text-align: center;
	font-family: monospace;
	content: '';
	/* animation: hide .8s linear; */
}
.page.flipped:after {
    /* content: '탭2'; */
    transform: rotateY(180deg);
    pointer-events: auto;
}
  /* to hide on flip animation */
@keyframes hide {
    0% {opacity: 0;}
    85% {opacity: 0;}
    100% {opacity: 1;}
}
.flipped {
	transform: rotateY(-180deg);
	pointer-events: none;
	/* animation: flip .3s linear;
	animation-fill-mode: forwards; */
}
  
.no-anim,
.no-anim:after {
	animation: none;/* disable animation when page loads */
}
@keyframes flip {
	to {
    	transform: rotateY(-180deg);    
    }
}
@keyframes unflip {
	from {
		transform: rotateY(-180deg);    
    }
  	to {
    	transform: rotateY(0deg);    
	}
}
</style>