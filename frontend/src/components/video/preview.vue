<template>
    <div style="margin:30px 0px 0px 30px;">
        <div class="dash">
            <font size=4>동영상 미리보기</font>
        </div>
		<div class="dash" style="height:620px; padding:20px;">
			<div class='main'>
				<div id='media-player' width="100%">
					<video id='media-video' controls width="100%">
					<source src='https://blog.kakaocdn.net/dn/LTyNQ/btqGtnA9vZi/UAk9kxQpS8xz8TkPk2BdW0/tfile.mp4'>
					</video>
				</div>
				<div id='media-controls' class='ctrl-box'>
					<button id='play-pause-button' class='play' title='play' @click='togglePlayPause'>
					<div class="playfront" id="play-disp">
						<span class="fa fa-play"></span>
					</div>
					<div class="playback" id="user-avi"></div>
					</button>
					<a class="data-name">
					<div id="play-bar" class="play-bar"></div>
					<div class="play-bar-border"></div>
					</a>
				</div>
			</div>
        </div>
        <div class="dash pointer">
            <font size=4>동영상 변환하기</font>
        </div>
    </div>
</template>

<script>
export default {
	mounted() {
		let mediaPlayer = document.getElementById('media-video')
		mediaPlayer.controls = false
		mediaPlayer.addEventListener('timeupdate', updateProgressBar, false)

		function updateProgressBar() {
			var progressBar = document.getElementById('play-bar');
			var percentage = Math.floor((100 / mediaPlayer.duration) * mediaPlayer.currentTime)
			progressBar.style.width = percentage + '%'
			if(mediaPlayer.ended) {
				let btn = document.getElementById('play-pause-button')
				let ovr = document.getElementById('play-disp')
				btn.title = 'play'
				ovr.innerHTML = '<span class="fa fa-play"></span>'
				progressBar.style.width = '0%'
			}
		}
	},
	methods: {
		togglePlayPause() {
			let mediaPlayer = document.getElementById('media-video')
			let btn = document.getElementById('play-pause-button')
			let ovr = document.getElementById('play-disp')
			if (mediaPlayer.paused || mediaPlayer.ended) {
				btn.title = 'pause'
				ovr.innerHTML = '<span class="fa fa-pause"></span>'
				mediaPlayer.play()
			} else {
				btn.title = 'play'
				ovr.innerHTML = '<span class="fa fa-play"></span>'
				mediaPlayer.pause()
			}
		}
	}
}
</script>

<style scoped>
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css);
.main {
	position: absolute;
	width: 500px;
	height: 500px;
}

#media-player {
	display: block;
	position: absolute;
	top: 0;
	bottom: 64px;
	left: 0;
	right: 0;
}

#media-video {
	display: block;
	width: 100%;
	height: 100%;
	object-fit: contain;
}

.ctrl-box {
	display: block;
	position: absolute;
	left: 0;
	right: 0;
	height: 64px;
	bottom: 0;
	margin-top: -5px;
}

.play {
	display: block;
	position: absolute;
	width: 50px;
	height: 50px;
	top: 7px;
	left: 7px;
	border: none;
	outline: none;
	color: white;
	background: #8D6262;
	border-radius: 50%;
	overflow: hidden;
}

.playback {
	display: block;
	position: absolute;
	height: 50px;
	width: 50px;
	top: 0;
	left: 0;
	border-radius: 50%;
	background-size: 100%;
	background-repeat: no-repeat;
	background-position: center;
	transition: transform 200ms;
	transform: scale(0);
}

.playfront {
	display: block;
	position: absolute;
	height: 50px;
	width: 50px;
	line-height: 50px;
	top: 0;
	left: 0;
	border-radius: 50%;
	background-color: #8D6262;
	z-index: 5;
}

.data-name {
	color: black;
	display: block;
	position: absolute;
	left: 64px;
	right: 7px;
	top: 4px;
	height: 50px;
	line-height: 32px;
	font-size: 1em;
	text-decoration: none;
	overflow: hidden;
}

.data-video {
	font-family: lato, sans;
	color: black;
	display: block;
	position: absolute;
	left: 64px;
	right: 0;
	bottom: 0;
	height: 32px;
	line-height: 32px;
	font-size: 0.8em;
	text-decoration: none;
	overflow: hidden;
}

.play-bar {
	width: 0%;
	display: block;
	position: absolute;
	bottom: 7px;
	left: 0;
	right: 0;
	height: 30px;
	background-color: #8D6262;
}
.play-bar-border {
	display: block;
	position: absolute;
	bottom: 7px;
	left: 0;
	right: 0;
	height: 30px;
	border: solid 1px #8D6262;
}

</style>