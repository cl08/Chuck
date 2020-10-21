import matplotlib.pyplot as plt
import face_recognition as fr
from sklearn.cluster import DBSCAN
import numpy as np
import os
import cv2
from PIL import Image

#pip install dlib  < 파일로 다운받아야 함.
#pip install face_recognition
#pip install pillow
#pip install opencv-python

# 얼굴을 인식, 크롭 후 face_list 반환
def get_face(picture): 
    face_list = []
    locations = fr.face_locations(picture)
    if(len(locations) > 0):
        for location in locations:
            top, right, bottom, left = location
            face_image = picture[top:bottom, left:right]
            face_list.append(face_image)
    return face_list

# 사진을 USER 폴더 내 사진들과 비교하여 인물 분류 후 DB에 Insert
def cluster(faces, userid):

    # 폴더 생성(이미 폴더가 있다면 생성 X), 폴더의 파일들을 읽는다.
    os.makedirs(userid, exist_ok=True)
    files = os.listdir('C:/Users/multicampus/Desktop/python/' + userid)

    # 폴더 내 파일들을 인코딩
    encodings = []
    
    for file in files:
        path = os.path.join(userid, file)
        image = fr.load_image_file(path)
        # b, g, r = cv2.split(image)
        # image = cv2.merge([r, g, b])
        plt.imshow(image)
        plt.show()
        encodings.extend(fr.face_encodings(image))

    for face in faces:
        files = os.listdir('C:/Users/multicampus/Desktop/python/' + userid)
        filepath = os.path.join(userid, str(len(files) + 1) + ".jpg")   # 파일 갯수를 이용하여 파일명 지정
        cv2.imwrite(filepath, face)  # 저장

        cur_enc_ext = fr.face_encodings(face)
        cur_encoding = fr.face_encodings(face)[0]

        #아이디별 첫 사진 예외처리
        if not encodings:
            print("비교할 사진이 없다.")
            encodings.extend(cur_enc_ext)
            continue

        # 현재 사진과의 거리 산출
        distances = fr.face_distance(cur_encoding, encodings)

        # 현재 사진도 encodings에 포함시킨다.
        encodings.extend(cur_enc_ext)

        # 사진들 중, 가장 근접한 사진을 찾는다.
        index = np.argmin(distances)
        min_value = distances[index]
        print("최소 - " + str(index + 1) + "번 째의 사진 : " + str(min_value))
        
        # 가장 근접한 사진과의 거리가 0.35 미만이면
        if min_value < 0.35:
            print(str(len(files) + 1) + "은 " + str(index + 1) + "번 그룹입니다.")
        else:
            print(str(len(files) + 1) + "번으로 새 그룹에 할당 ")
    
        # db에 (group, picture) 저장

    # print(encodings)
    # clt = DBSCAN()
    # clt.fit(encodings)

    

    # label_ids = np.unique(clt.labels_)
    # num_unique_faces = len(np.where(label_ids > -1)[0])
    # # indexes = np.where(clt.labels_ == label_id)[0]
    # print("라벨 List : " + str(label_ids))
    # print("유효한 라벨 개수 : " + str(num_unique_faces))

    
    # for label_id in label_ids:
    #     indexes = np.where(clt.labels_ == label_id)[0]
    #     print("라벨 : " + str(label_id))
    #     print("해당 라벨의 인덱스들 : " + str(indexes))


def main():
    face_list = []
    face_list.extend(get_face(fr.load_image_file('wendy3.jpg')))
    cluster(face_list, 'average10@naver.com')

    # plt.rcParams["figure.figsize"] = (5, 5)


if __name__ == "__main__":
    main()