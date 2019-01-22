import Node
import subprocess

class Parser:
    def __init__(self):
        self.__NodeList = []
        self.__url = ""
        self.__width = 0
        self.__height = 0

    def parsehtml(self):

        response = subprocess.Popen('node Parser.js', stdin=subprocess.PIPE, stdout=subprocess.PIPE, shell=True)
        result = response.communicate(input=self.__url.encode())[0]
        result = result.decode().split('\n')
        result = result[0:len(result)-1]
        self.__width = result[len(result)-2]
        self.__height = result[len(result)-1]
        node = []
        for i in range(0, len(result)-2):
            if i % 9 == 0:
                node.clear()
            node.append(result[i])
            if i % 9 == 8:
                self.makenode(node)


    def makenode(self, node):
        newnode = Node.Node(node)
        self.__NodeList.append(newnode)

    def getnodelist(self):
        return self.__NodeList

    def getwidth(self):
        return self.__width

    def getheight(self):
        return self.__height

    def seturl(self, url):
        self.__url = url
