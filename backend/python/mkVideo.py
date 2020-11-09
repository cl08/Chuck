import re
import os
import cv2
import numpy as np
from PIL import Image
import matplotlib.pyplot as plt
from moviepy.editor import *

# pip install moviepy

def makeVideo(paths, userid, music):
    paths = paths[:-1]
    pathList = paths.split(":")

    print(pathList)

    fps, width, height = 10, 500, 500
    frame_array = []

    for idx , path in enumerate(pathList) : 
        if(idx == len(pathList) - 1):
            break
        weight, img2, img1 = 0, cv2.resize(cv2.imread(pathList[idx]), (width, height)), cv2.resize(cv2.imread(pathList[idx+1]), (width, height))
        frame_array.append(img2)
        frame_array.append(img2)
        # fade in / fade out
        while(weight <= 1.0):
            postWeight = 1.0 - weight
            dst = cv2.addWeighted(img1, weight, img2, postWeight, 0)
            frame_array.append(dst)
            weight = weight + 0.2
    out = cv2.VideoWriter("videos/" + userid + "/middle.mp4",cv2.VideoWriter_fourcc(*'DIVX'), fps, (width, height))
    print(len(frame_array))
    for i in range(len(frame_array)):
        out.write(frame_array[i])
    out.release()


    # 오디오 입히기
    videoclip = VideoFileClip("videos/" + userid + "/middle.mp4")
    audioclip = AudioFileClip(music)
    audioclip.duration = videoclip.duration
    videoclip.audio = audioclip
    videoclip.write_videofile("videos/" + userid + "/final.mp4")


def main():
    makeVideo("dataset\\1.jpg:dataset\\102701997.2.jpg:dataset\\1809170726057320_w.jpg:dataset\\2.jpg:dataset\\20191108181733_5dc532adcc402_1.jpg:", "5")



    # 동영상 제작
    # paths = [os.path.join('dataset', path) for path in os.listdir('dataset')]
    # print(paths)
    # fps, width, height = 10, 500, 500
    # frame_array = []

    # for idx , path in enumerate(paths) : 
    #     if(idx == len(paths) - 1):
    #         break
    #     weight, img2, img1 = 0, cv2.resize(cv2.imread(paths[idx]), (width, height)), cv2.resize(cv2.imread(paths[idx+1]), (width, height))
    #     frame_array.append(img2)
    #     frame_array.append(img2)
    #     # fade in / fade out
    #     while(weight <= 1.0):
    #         postWeight = 1.0 - weight
    #         dst = cv2.addWeighted(img1, weight, img2, postWeight, 0)
    #         frame_array.append(dst)
    #         weight = weight + 0.2
    # out = cv2.VideoWriter("middle.mp4",cv2.VideoWriter_fourcc(*'DIVX'), fps, (width, height))
    # print(len(frame_array))
    # for i in range(len(frame_array)):
    #     out.write(frame_array[i])
    # out.release()


    # # 오디오 입히기
    # videoclip = VideoFileClip("middle.mp4")
    # audioclip = AudioFileClip("Fingertips.mp3")
    # audioclip.duration = videoclip.duration
    # videoclip.audio = audioclip
    # videoclip.write_videofile("final.mp4")


if __name__ == "__main__":
    main()
