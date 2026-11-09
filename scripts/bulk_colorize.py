
import cv2
import numpy as np
import pygame
import sys
import os

def colorizeImg(in_file, out_file, r, g, b):
	img = cv2.imread(in_file, cv2.IMREAD_UNCHANGED)
	hh, ww = img.shape[:2]

	color_img = np.full((hh, ww, 3), (b, g, r), dtype=np.float32)

	bgr = img[:, :, 0:3]
	alpha = img[:, :, 3]

	bgr = bgr.astype(np.float32)
	mult = cv2.multiply(bgr, color_img)
	mult = mult.astype(np.uint8)

	result = cv2.cvtColor(mult, cv2.COLOR_BGR2BGRA)
	result[:, :, 3] = alpha

	cv2.imwrite(out_file, result)


def main():
	pygame.init()

	div = 128

	color = int(sys.argv[1], 16)
	red = (color >> 16) & 0xff
	green = (color >> 8) & 0xff
	blue = color & 0xff

	prepend = ""
	if len(sys.argv) > 2:
		prepend = sys.argv[2]

	in_files = os.listdir("in")

	for f in in_files:
		print(f)
		colorizeImg("in/" + f, "out/" + prepend + f, red / div, green / div, blue / div)

if __name__ == "__main__":
	main()