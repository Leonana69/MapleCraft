import os
import datetime
import argparse
import cv2
import numpy as np

from os.path import exists
from path import ItemsInitPath
from path import LangPath
from path import modId
from path import ItemsPath


# origin = [[14, 200], [33, 200], [32, 200], [33, 200], [32, 200], [33, 200], [36, 200], [33, 200]]
origin = [[39, 36], [48, 32], [55, 49], [42, 43], [31, 34]]

def main(name, count):
	imgs = []
	leftWidth = 0
	rightWidth = 0
	topHeight = 0
	bottomHeight = 0
	for i in range(0, count):
		img = cv2.imread(name + "_hit_" + str(i) + ".png", cv2.IMREAD_UNCHANGED)
		imgs.append(img)

		if img.shape[1] - origin[i][0] > rightWidth:
			rightWidth = img.shape[1] - origin[i][0]
		if img.shape[0] - origin[i][1] > bottomHeight:
			bottomHeight = img.shape[0] - origin[i][1]
		if origin[i][0] > leftWidth:
			leftWidth = origin[i][0]
		if origin[i][1] > topHeight:
			topHeight = origin[i][1]

	totalWidth = leftWidth + rightWidth
	totalHeight = topHeight + bottomHeight

	print(leftWidth, rightWidth, topHeight, bottomHeight)

	emptyImg = np.full((totalHeight, totalWidth * count, 4), (0, 0, 0, 0), dtype=np.uint8)

	for i in range(0, count):
		yOffset = topHeight - origin[i][1]
		xOffset = leftWidth - origin[i][0] + i * totalWidth
		emptyImg[yOffset : yOffset + imgs[i].shape[0], xOffset : xOffset + imgs[i].shape[1]] = imgs[i]
		print(imgs[i].shape[0], imgs[i].shape[1])
	cv2.imwrite(name + "_hit.png", emptyImg)
	return
	

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--i", help="input file name",
                        type=str, required=True)
    parser.add_argument("--n", help="number of files",
                        type=int, required=True)

    args = parser.parse_args()

    main(args.i, args.n)