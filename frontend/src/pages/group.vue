<template>
    <div class="group-select">
        <br>
        <h1>그룹 선택 페이지</h1>
        <!-- 충돌 방지하기 위해 기능 임시로 구현한 곳 -->
        <!-- <a @click="logout">logout</a>
        <br><br>
        <h3>그룹원 초대하기
            <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">
                <v-btn
                    icon
                    v-bind="attrs"
                    v-on="on"
                    @click="inviteGroupUser()"
                >
                    <v-icon>
                    mdi-account-multiple-plus
                    </v-icon>
                </v-btn>
                </template>
            <span>회원 추가</span>
            </v-tooltip>
        </h3> -->
        <br>
        <!-- <router-link to="/diary">그룹 생성</router-link> -->
        <template>
            <div class="global-wrapper">
                <div class="floor-wrapper">
                    <div class="floor">
                        <div class="book-list">       
                            <template v-for="(book, i) in $data.books">
                                <v-tooltip bottom :key="i+1">
                                    <template v-slot:activator="{ on, attrs }">    
                                        <div 
                                            class="book-item"
                                            @click="selectBook(book)"
                                            v-bind="attrs"
                                            v-on="on"
                                            v-if="i+1 !== $data.books.length"
                                        >
                                            <img class="cover" :src="require(`../assets/books/book${(i % 11) + 1 }.png`)"/>
                                            <div class="back"></div>
                                        </div>
                                        <div 
                                            class="book-item"
                                            @click="addGroup()"
                                            v-bind="attrs"
                                            v-on="on"
                                            v-else
                                        >
                                            <img class="cover" :src="require(`../assets/books/blink_book.png`)"/>
                                            <div class="back"></div>
                                        </div>
                                    </template>
                                    <span>{{book.name}}</span>
                                </v-tooltip>
                            </template>
                        </div>
                    </div>
                </div>
                <template v-if="$data.selected">
                    <div class="menu-overlay" @click="closeMenu"></div>
                </template>
                <transition name="menu" tag="div">
                    <template v-if="$data.selected">
                        <div class="side-menu">
                            <div  v-if="this.bookgen === false">
                                <p
                                    class="title"
                                    style="
                                        font-size: 18px;
                                        font-weight: bold;
                                        margin-bottom: 20px;
                                        position: relative;
                                        top: 5vh;"
                                >
                                {{$data.selectedBook.name}}</p>
                                <div
                                    class="description"
                                    style="
                                        line-height: 1.7;
                                        font-size: 12px;
                                        position: relative;
                                        top: 5vh;
                                        width: 200px;
                                        margin: 0 auto;"
                                >
                                {{$data.selectedBook.intro}}</div>
                                <div
                                    class="picker"
                                    @click="selectGroup()"
                                    style="
                                        color: #8D6262;
                                        line-height: 2;
                                        font-size: 15px;
                                        position: relative;
                                        bottom: -25vh;
                                        cursor: pointer;
                                        width: 200px;
                                        margin: 5px auto;"
                                >
                                그룹 선택</div>
                            </div>
                            <div v-else>
                                <br/>
                                <img :src="require('../assets/logo.svg')" style="height: 5vh; width: 10vw; position: absolute; left: 7vw;"/>
                                <br/><br/><br/>
                                <input 
                                    class="description"
                                    v-model="$data.selectedBook.name"
                                    placeholder="그룹명을 입력해주세요."
                                    style="
                                        line-height: 1.7;
                                        font-size: 15px;
                                        position: relative;
                                        width: 200px;
                                        margin: 0 auto;"
                                />
                                <br/><br/>
                                <textarea
                                    class="description"
                                    v-model="$data.selectedBook.intro"
                                    placeholder="그룹 소개를 입력해주세요."
                                    style="
                                        line-height: 1.7;
                                        font-size: 12px;
                                        position: relative;
                                        top: 2vh;
                                        width: 200px;
                                        margin: 0 auto;"
                                />
                                <br/><br/><br/>
                                <p
                                    class="create"
                                    @click="uploadGroup"
                                    style="
                                    color: #8D6262;
                                    cursor: pointer;
                                    line-height: 1.7;
                                    font-size: 20px;
                                    bottom: -7vh;
                                    position: relative;"
                                >
                                그룹 만들기</p>
                            </div>
                        </div>
                    </template>
                </transition>
            </div>
        </template>
    </div>
</template>

<script>
import api from '@/utils/api';
import axios from 'axios';

export default {
    data() {
        return {
            books: [],
            selected: false,
            bookgen: false,
            selectedBook: {},
        };
    },
    mounted() {
        if (!window.Kakao) {
            const script = document.createElement('script');
              script.onload = () => window.Kakao.init('d1baf2cad3354a9138989baea6e65995');
            script.src = '/js/kakao.min.js';
            document.head.appendChild(script);
        }
        axios
            .get("http://k3a206.p.ssafy.io:8888/chcuk/groups/of/groups", {
                headers: {
                    token: sessionStorage.getItem('token')
                }
            })
            .then((res) => {
                this.$data.books = this.$data.books.concat(res.data);
                this.$data.books = this.$data.books.concat({name: "그룹생성", intro: ""});
                var cnt = this.$data.books.length / 4;
                if(cnt >= 4) $('.floor-wrapper > .floor').css('top', '0vh');
            });
        var w = window.innerWidth
        var h = window.innerHeight
        $('.group-select').height(h);
        $('.group-select').width(w);
        window.addEventListener("resize", this.handleResize)
    },

    methods: {
        selectGroup() {
            this.$router.push({name: 'diary', parmas: {groupId: this.selectedBook.id}})
        },
        uploadGroup() {
            axios
                .post(`http://k3a206.p.ssafy.io:8888/chcuk/groups`, {
                    name: this.selectedBook.name,
                    intro: this.selectedBook.intro
                }, {
                    headers: {
                        token: sessionStorage.getItem('token')
                    }
                }).then((res) => {
                    this.$data.books.splice(this.books.length - 1, 0, res.data);
                    this.bookgen = false;
                    this.selected = false;
                    this.selectedBook = {};
                })
        },
        handleResize(e){
            var w = window.innerWidth
            var h = window.innerHeight
            $('.group-select').height(h);
            $('.group-select').width(w);
        },
        logout() {
            this.$store.dispatch('logout');
            this.$router.push("/");
        },
        inviteGroupUser() {
            if(sessionStorage.getItem("ID") === "1") {
                alert("테스트 아이디는 카카오 초대가 불가능합니다.")
            } else {
                window.Kakao.Link.sendCustom({
                    templateId: 39047,
                    templateArgs: {
                        key: sessionStorage.getItem('token'),
                        group: "쓰는척", // this.groupName,
                        user: sessionStorage.getItem('NAME'),
                    },
                });
            }
        },
        selectBook(book) {
            this.bookgen = false;
            this.$data.selected = true;
            this.$data.selectedBook = book;
        },
        closeMenu() {
            this.$data.selected = false;    
        },
        addGroup() {
            this.selectedBook.name = '';
            this.selectedBook.intro = '';
            this.bookgen = true;
            this.$data.selected = true;
        }
    }
}
</script>

<style lang="scss" scoped>
.create {
    color: #8D6262;
    cursor: pointer;
    line-height: 1.7;
    font-size: 20px;
    bottom: -7vh;
    position: relative;
}
.group-select {
    overflow: hidden;
}
.global-wrapper {
  height: 100vh;
  overflow: scroll;
}
.menu-enter-active,
.menu-leave-active {
  transition: all 0.5s cubic-bezier(0.15, 0.6, 0.52, 1);
}
.menu-enter, .menu-leave-to /* .fade-leave-active below version 2.1.8 */ {
  transform: translateY(100%);
  opacity: 0;
}

.floor-wrapper {
  width: 1072px;
  perspective: 500px;
  margin: 0 auto;
  > .floor {
    transform-style: preserve-3d;
    transform: rotateX(20deg) rotateZ(-0deg) translateY(-50%);
    position: relative;
    top: 20vh;
    width: 880px;
    margin-left: 100px;
  }
}

.book-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.book-item {
  width: 200px;
  margin-bottom: 30px;
  transform: translateZ(2px);
  cursor: pointer;
  transform-style: preserve-3d;
  position: relative;

  &::before,
  &::after {
    content: "";
    display: block;
    position: absolute;
  }

  &::before {
    background-color: #c0c0c0;
    height: 100%;
    width: 10px;
    top: 0;
    left: 0;
    transform-origin: left;
    transform: rotateY(90deg);
  }

  &::after {
    background: linear-gradient(to bottom, #fff, #dadada);
    height: 10px;
    width: 100%;
    top: 100%;
    transform-origin: top;
    transform: rotateX(-180deg);
  }

  &:hover {
    > .cover {
      animation: blink .5s linear alternate infinite;
      box-shadow: 0 0 10px #8D6262;
    }
  }

  > .cover {
    display: block;
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition: filter .2s;
  }

  > .back {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    transform: translateZ(-10px);
    box-shadow: 0 0 10px rgba(black, 0.2);
  }
  
  @keyframes blink {
    from {
      filter: brightness(1);
    }
    
    to {
      filter: brightness(1.2);
    }
  }
}

.side-menu {
  width: 480px;
  height: 100vh;
  overflow-y: scroll;
  position: absolute;
  top: 55vh;
  left: 76.4vh;
  z-index: 2;
  background-image: url("../assets/temp_hand_book2.png");
  padding: 2px 2px 8px 4px;
  box-sizing: border-box;

  > .title {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 20px;
    position: relative;
    top: 5vh;
  }

  > .description {
    line-height: 1.7;
    font-size: 12px;
    position: relative;
    top: 5vh;
    width: 200px;
    margin: 0 auto;
  }

  > .picker {
    line-height: 2;
    font-size: 15px;
    position: relative;
    bottom: -25vh;
    cursor: pointer;
    width: 200px;
    margin: 5px auto;
  }
}

.menu-overlay {
  width: 100vw;
  height: 100vh;
  background-color: rgba(black, 0.4);
  position: absolute;
  top: 0;
  left: 0;
}
</style>>