import re
import os
import cv2
from PIL import Image
import matplotlib.pyplot as plt
def main():

    # method 2, 현재 문제점 : 동영상은 만들어지나 크기조정하면 매우 깨짐.
    paths = [os.path.join('dataset', path) for path in os.listdir('dataset')]
    print(paths)
    fps = 1
    frame_array = []
    for idx , path in enumerate(paths) : 
        img = cv2.imread(path)
        print(img.shape)
        img.resize((4000, 4000, 3))
        plt.imshow(img)
        plt.show()
        # img = img.resize((1080, 1080))
        
        # img = img.resize((1080, 1080))
        height, width, layers = img.shape
        size = (width,height)
        frame_array.append(img)
    out = cv2.VideoWriter("method2.mp4",cv2.VideoWriter_fourcc(*'DIVX'), fps, size)
    # out = cv2.VideoWriter("method2.mp4",cv2.VideoWriter_fourcc(*'MP4V'), fps, size)
    # fourcc = cv2.VideoWriter_fourcc(*'XVID')
    # fourcc = cv2.VideoWriter_fourcc(*'MP4V')
    # out = cv2.VideoWriter('output.avi',fourcc, fps, (1080,1080))
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
