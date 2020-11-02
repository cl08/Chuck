import re
import os
import cv2
import numpy as np
from PIL import Image
import matplotlib.pyplot as plt
from moviepy.editor import *

# pip install moviepy


def main():

    # img1 = cv2.imread('dataset/1.jpg')
    # img2 = cv2.imread('dataset/2.jpg')
    # img1 = cv2.resize(img1, (500, 500))
    # img2 = cv2.resize(img2, (500, 500))
    
    # alpha = 0.5
    # dst = cv2.addWeighted(img1, alpha, img2, (1-alpha), 0)
    # cv2.imshow('cv2.addWeighted', dst)
    # cv2.waitKey(0)
    # cv2.destroyAllWindows()



    # a = 0.0

    # while(a <= 1.0):

    #     img1 = cv2.imread('dataset/1.jpg')
    #     img2 = cv2.imread('dataset/2.jpg')


    #     # 블렌딩하는 두 이미지의 크기가 같아야함
    #     width = img1.shape[1]
    #     height = img1.shape[0]
    #     img2 = cv2.resize(img2, (width, height))

    #     # img1 사진은 점점 투명해지고 img2 사진은 점점 불투명해짐
    #     b = 1.0 - a
    #     dst = cv2.addWeighted(img1, a, img2, b, 0)
    #     cv2.imshow('dst',dst)
    #     cv2.waitKey(0)

    #     print( a, " ", b)

    #     a = a + 0.2


    # cv2.destroyAllWindows()

    # 동영상 제작
    paths = [os.path.join('dataset', path) for path in os.listdir('dataset')]
    print(paths)
    fps, width, height = 2, 500, 500
    frame_array = []

    # for path in paths:
    #     cv2.imshow('aaa', cv2.imread(path))
    #     cv2.waitKey(0)

    for idx , path in enumerate(paths) : 
        if(idx == len(paths) - 1):
            break
        weight, img2, img1 = 0, cv2.resize(cv2.imread(paths[idx]), (width, height)), cv2.resize(cv2.imread(paths[idx+1]), (width, height))
        frame_array.append(img2)
        while(weight <= 1.0):
            postWeight = 1.0 - weight
            dst = cv2.addWeighted(img1, weight, img2, postWeight, 0)
            # cv2.imshow('dst', dst)
            # cv2.waitKey(0)
            frame_array.append(dst)
            weight = weight + 0.2
    out = cv2.VideoWriter("method2.mp4",cv2.VideoWriter_fourcc(*'DIVX'), fps, (width, height))
    print(len(frame_array))
    for i in range(len(frame_array)):
        out.write(frame_array[i])
    out.release()


    # 오디오 입히기
    videoclip = VideoFileClip("method2.mp4")
    audioclip = AudioFileClip("Fingertips.mp3")
    audioclip.duration = videoclip.duration
    videoclip.audio = audioclip
    videoclip.write_videofile("new.mp4")


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
