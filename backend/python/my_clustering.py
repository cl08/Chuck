import matplotlib.pyplot as plt
import face_recognition
from sklearn.cluster import DBSCAN
import numpy as np
import os
import cv2
import pickle
from PIL import Image
from imutils import build_montages
from imutils import paths
# pip install PyMySQL
import pymysql;

#pip install dlib  < 파일로 다운받아야 함.
#pip install face_recognition
#pip install pillow
#pip install opencv-python

def encode(groupId, imagePaths):

    files = os.listdir('/home/ubuntu/s03p31a206/backend/python/')

    # imagePaths = list(paths.list_images("dataset"))
    data = []

    for (i, imagePath) in enumerate(imagePaths):
        print("[INFO] processing image {}/{}".format(i + 1, len(imagePaths)))
        image = cv2.imread(imagePath)
        rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
        boxes = face_recognition.face_locations(rgb, model="hog") # hor or cnn
        encodings = face_recognition.face_encodings(rgb, boxes)
        d = [{"imagePath": imagePath, "loc": box, "encoding": enc} for (box, enc) in zip(boxes, encodings)]
        data.extend(d)
    
    # 덧붙여 기록하는 것이 아니라, 자료구조 자체에 추가해야 되기 때문에 가져와서 추가해서 다시 저장해줄거임.
    if(groupId + ".pickle" in files):
        print("ㄹㅇㅋㅋ")
        datas = pickle.loads(open(groupId + ".pickle", "rb").read())
        data.extend(datas)
    f = open(groupId + ".pickle", "wb")
    f.write(pickle.dumps(data))
    f.close()

    cluster(groupId)


def clustering(groupId):
    data = pickle.loads(open(groupId + ".pickle", "rb").read())
    print(data)
    data = np.array(data)
    encodings = [d["encoding"] for d in data]

    clt = DBSCAN(metric="euclidean")
    clt.fit(encodings)

    labelIDs = np.unique(clt.labels_)
    numUniqueFaces = len(np.where(labelIDs > -1)[0])
    print("[INFO] # unique faces: {}".format(numUniqueFaces))

    os.makedirs(groupId + "_faces", exist_ok=True)
    result = []

    im = cv2.imread('dataset\99333550.1.jpg')
    print(im)

    for labelID in labelIDs:
        print("[INFO] faces for face ID: {}".format(labelID))
        idxs = np.where(clt.labels_ == labelID)[0]
        idxs = np.random.choice(idxs, size=min(25, len(idxs)), replace=False)
        faces = []
        pathNames = []
        for i in idxs:
            image = cv2.imread(data[i]["imagePath"])
            print("check")
            print(data[i]["imagePath"])
            print(image)
            pathNames.append("/home/ubuntu/s03p31a206/backend/python/" + data[i]["imagePath"])
            (top, right, bottom, left) = data[i]["loc"]
            print(top, right, bottom, left)
            face = image[top:bottom, left:right]
            face = cv2.resize(face, (96, 96))
            faces.append(face)
            
        if labelID != -1:
            cv2.imwrite(groupId + "_faces/" + str(labelID) + ".jpg", faces[0])
            result.append({"rep" : "/home/ubuntu/s03p31a206/backend/python/" + groupId + "_faces/" + str(labelID) + ".jpg", "paths" : list(set(pathNames))})

    return {"info" : result}


def main():
    # encode('average10@naver.com', ['dataset/1.jpg', 'dataset/2.jpg'])
    result = cluster('average10@naver.com')
    print(result)

if __name__ == "__main__":
    main()