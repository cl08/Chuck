<template>
    <div style="padding:30px 30px 0px 0px;">
        <div>
        <img src="../../assets/title/chuck_write.svg" class="subtitle">
        </div>
        <div style="margin:20px 0px 20px 0px;">
            <font size=4>최대 12장의 사진을 업로드할 수 있습니다.</font>
        </div>
        <div class="dash" style="height:230px;">
            <el-upload 
            drag
            multiple
            action="http://k3a206.p.ssafy.io:8888/chuck/pictures/upload"
            :data="{groupId:this.$store.state.selectedGroup.id}"
            :limit=12
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
        <div style="padding:10px;">
            <ul class="el-upload-list el-upload-list--picture-card" style="padding:0px;">
                <li v-for="index in 8" :key="index" class="el-upload-list__item is-ready">
                    <img src="https://image.auction.co.kr/itemimage/19/8e/a2/198ea2c571.jpg" class="el-upload-list__item-thumbnail">
                    <span class="el-upload-list__item-actions">
                        <span class="el-upload-list__item-preview">
                            <i class="el-icon-zoom-in"></i>
                        </span>
                        <span class="el-upload-list__item-delete">
                            <i class="el-icon-delete"></i>
                        </span>
                    </span>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            thumbnailList: '',
            disabled: false,
        };
    },
    methods: {
        beforeImageUpload(file) {
            console.log("오잉")
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
            alert("사진은 최대 12개까지 업로드 할 수 있습니다.")
        },
        handleSuccess(res, file) {
            // alert("업로드 성공")
            console.log(res)
            console.log(file)
            // 이미지 배열에 추가
        },
        handleRemove(file, fileList) {
            // alert("삭제 성공")
            console.log(file)
            console.log(fileList)
            // 이미지 배열에서 제거
        }
    }
}
</script>
<style scoped>
</style>