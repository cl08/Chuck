import re
import os
import cv2
from PIL import Image
import matplotlib.pyplot as plt
from moviepy.editor import *

# pip install moviepy


def main():

    # 동영상 제작
    paths = [os.path.join('dataset', path) for path in os.listdir('dataset')]
    print(paths)
    fps = 1
    frame_array = []
    for idx , path in enumerate(paths) : 
        img = cv2.imread(path)
        print(img.shape)
        # img.resize((500, 500, 3))
        # cv2.resize(img, (4000, 4000), interpolation=cv2.INTER_AREA)
        img = cv2.resize(img, (500, 500)) 
        # plt.imshow(img)
        # plt.show()
        height, width, layers = img.shape
        size = (width,height)
        frame_array.append(img)
    out = cv2.VideoWriter("method2.mp4",cv2.VideoWriter_fourcc(*'DIVX'), fps, size)
    print(len(frame_array))
    for i in range(len(frame_array)):
        out.write(frame_array[i])
    out.release()







    # 동영상을 재생하는 코드.
    # capture = cv2.VideoCapture("method2.mp4")
    # while True:
    #     if(capture.get(cv2.CAP_PROP_POS_FRAMES) == capture.get(cv2.CAP_PROP_FRAME_COUNT)):
    #         capture.open("method2.mp4")

    #     ret, frame = capture.read()
    #     print(ret)
    #     print(frame)
    #     cv2.imshow("VideoFrame", frame)

    #     if cv2.waitKey(33) > 0: break
    # capture.release()
    # cv2.destroyAllWindows()


if __name__ == "__main__":
    main()
