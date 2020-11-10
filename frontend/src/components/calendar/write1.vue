<template>
    <div style="padding:30px 30px 0px 0px;">
        <div>
        <img src="../../assets/title/chuck_write.svg" class="logo">
        </div>
        <div style="margin:20px 0px 20px 0px;">
            <font size=4>최대 10장의 사진을 업로드할 수 있습니다.</font>
        </div>
        <div class="dash" style="height:230px;">
            <el-upload 
            drag
            multiple
            action="http://k3a206.p.ssafy.io:8888/chuck/pictures/upload"
            :data="{groupId:this.getSelectedGroup.id}"
            :limit=10
            :show-file-list="false"
            :auto-upload="true"
            :before-upload="beforeImageUpload"
            :on-success="handleSuccess"
            :on-exceed="handleExceed"
            >
                <img src="../../assets/uploadIcon.svg" style="margin-top:40px;">
                <div class="el-upload__text"><em>클릭</em>하거나 <em>드래그</em>하여 이미지를 업로드 하세요.</div>
            </el-upload>
        </div>
        <div style="padding:28px; text-align:left;">
            <ul class="el-upload-list el-upload-list--picture-card" style="padding:0px;">
                <li v-for="(image, index) in $data.imageList" :key="index" class="el-upload-list__item is-ready">
                    <img :src="image" class="el-upload-list__item-thumbnail">
                    <span class="el-upload-list__item-actions">
                        <span class="el-upload-list__item-preview">
                            <!-- <i class="el-icon-zoom-in"></i> -->
                            <change
                                :index="index"
                                @changeImage="changeImage"
                            ></change>
                        </span>
                        <span class="el-upload-list__item-delete" @click="handleRemove(image, index)">
                            <v-tooltip bottom>
                                <template v-slot:activator="{ on, attrs }">
                                    <i 
                                        icon
                                        class="el-icon-delete"
                                        v-bind="attrs"
                                        v-on="on"
                                    ></i>
                                </template>
                                <span>삭제</span>
                            </v-tooltip>
                        </span>
                    </span>
                </li>
            </ul>
        </div>
        <div style="padding:0px 28px; text-align:right" v-if="imageList.length > 0">
            <font size=5 class="pointer" @click="removeAll">전체삭제</font>
        </div>
    </div>
</template>

<script>
import api from '@/utils/api';
import eventBus from '@/utils/EventBus';
import { mapGetters } from 'vuex'

export default {
    data() {
        return {
            disabled: false,
            imageList: [],
            imageChange: false,
        };
    },
    computed: {
        ...mapGetters([
            'getSelectedGroup',
        ]),
    },
    components: {
        Change: () => import('@/components/calendar/change.vue'),
    },
    created() {
        eventBus.$on('uploadImages', (data) => {
            this.uploadImages(data);
        });
        eventBus.$on('clearWrite', () => {
            this.imageList = [];
        });
    },
    methods: {
        beforeImageUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isPNG = file.type === 'image/png';
            const isLt10M = file.size / 1024 / 1024 < 10;

            if (!(isPNG || isJPG)) {
                alert('JPG, PNG 포맷만 지원합니다.');
            }
            if (!isLt10M) {
                alert('파일 크기 제한은 10MB입니다.');
            }
            return (isJPG || isPNG) && isLt10M;
        },
        handleExceed(file, fileList){
            alert("사진은 최대 10장의 사진을 업로드 할 수 있습니다.")
        },
        handleSuccess(res, file) {
            this.imageList = this.imageList.concat(res);
        },
        handleRemove(file, index) {
            api.delete(`pictures/deleteByPath?path=${file}`).then(()=> {
                this.imageList.splice(index, 1);
            })
        },
        removeAll() {
            for(let i=0; i<this.imageList.length; i++) {
                api.delete(`pictures/deleteByPath?path=${this.imageList[i]}`).then(()=> {
                    this.imageList.splice(i--, 1)
                })
            }
        },
        uploadImages(diaryId) {
            const images = [];
            this.imageList.forEach(image => {
                images.push(image);
            });
            api.post(`pictures/insert`, {
                diary_id: diaryId,
                path_list: images,
            }).then((data) => {
                eventBus.$emit('uploadeDone');
                this.imageList = [];
            })
        },
        changeImage(data) {
            this.handleRemove(this.imageList[data.index], data.index+1);
            this.imageList.splice(data.index, 0, data.res)
        }
    }
}
</script>
<style scoped>
</style>