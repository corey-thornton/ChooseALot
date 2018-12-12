import cv2
import numpy as np
import sys
from PIL import Image


def openSpots(lotSize,ParkingLotImage,dimensions):

	
    #Image Processing
    img = cv2.imread(ParkingLotImage)
    gray_image = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    retval, threshold = cv2.threshold(gray_image,100,255,cv2.THRESH_BINARY_INV)
    im = Image.fromarray(threshold)
    im.save("thresholdImage.jpg")
    image2 = cv2.imread('thresholdImage.jpg')
    im2, contours, hierarchy = cv2.findContours(threshold,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)

    boxes = 0
    total_area = 0
    widths = []
    heights = []
    widthHeight = parseTuple(dimensions)

    for c in contours:
        x,y,w,h = cv2.boundingRect(c)
        greaterThan = w > widthHeight[0] and h > widthHeight[1]
        lessThan = w < (widthHeight[0] + 130) and h < (widthHeight[1] + 130)
        if greaterThan and lessThan:
            cv2.rectangle(image2,(x,y),(x+w, y+h),(0,255,0),2)
            widths.append(w)
            heights.append(h)
            total_area = (total_area + (w*h))

            boxes = boxes + 1
    avg_width = average(widths)
    avg_height = average(heights)

    avg_area = area(avg_width,avg_height)
    if(avg_area == 0):
        openspots = lotSize
    else:
        openspots =  float(lotSize) - (total_area // avg_area)
    outPut = ('There are ' +str(openspots) + ' open spots in ' + ParkingLotImage[:-4] + '\n')

    image3 = Image.fromarray(image2)
    image3.save("thresholdImage.jpg")
    '''
    cv2.imshow('original', img)
    cv2.imshow('hopeful', image2)
    
    cv2.waitKey(0)
    cv2.destroyAllWindows()
    
    print(total_area)
    print(avg_area)
    '''


    return outPut
    
def parseTuple(sTuple):
    
    sections = sTuple.split(',')
    firstInt = sections[0]
    secondInt = sections[1]
    newTuple =(int(firstInt[1:]),int(secondInt[:len(secondInt)-1]))
    return newTuple    

def average(ListOfNumbers):
    total = 0
    count = 0
    for i in ListOfNumbers:
        total = total + i
        count = count + 1
    if(count == 0):
        return 0
    else:
        return total/count

def area(width,height):
    return width * height



print(openSpots(sys.argv[1],sys.argv[2],sys.argv[3]))
#print(openSpots(14,'realisticLot1.jpg',(50,120)))

