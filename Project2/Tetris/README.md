![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s1.png)
![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s2.png)
![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s3.png)
![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s4.png)
![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s5.png)
![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s6.png)
![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s7.png)
![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s8.png)
![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s9.png)
![](https://github.com/gusalsdmlwlq/SE-2017/blob/master/Project2/s10.png)
# ���� ����
* �⺻���� ��Ʈ����
* 7������ ��
* ��.�� ����Ű�� ���� �̵�
* �� ����Ű�� ���� ȸ��
* �Ʒ� ����Ű, �����̽��� ���� ����
* �� ���� �Ͷ߸��� score +100
* score�� 1000 �ö� �� ���� stage up
* ���� ���� 15ĭ ���� 20ĭ

# ��� ����
* ��Ʈ������ �⺻ ��� ����
* �Ʒ� ����Ű, �����̽��� ���� ���ӽ�ų �� ����
* 15*20 �迭�� ��Ʈ������ ���� ����
* �� ���� 4*4 �迭�� ����
* ���� 7 �����̸� ���� �ٸ� ������ ����
* ���� �ٸ� ���̳� ���� ������ �з����� ����� ���
* �ؽ�Ʈ�� score�� stage�� ǥ��
* ���� score���� stage�� �ö�
* ���� �� ǥ��
* ���� ���� �� ���� ���� ǥ��
* stage�� �ö� �� ���� Ư�� ȿ���� �߰�
* stage 2,4,6 �޼��� �̹����� ��� ��� ����
* stage 3 �޼��� �̹����� ���� ���� �������� �ӵ� ����
* stage 5 �޼��� �̹����� ���� �������� ������ ����
* 1000���� �޼����� ���ϰ� ���� õ�忡 ��� ������ ���� ��� �ڵ����� ������ �ٽ� ���۵�
* ������ �����ϸ� Ư�� ȿ���� ���� '�� ��' <������� ���� �� ����> Ȩ�������� ���
* Battlenet ���� �⺻ ��ο� ��ġ �Ǿ� ������ Ȩ�������� ��� �� Battlenet ���� ������ �����Ŵ
* <������� ���� �� ����>�� ��ġ �Ǿ� ������ �ٷ� ���� ����
* bgm �߰�
* stage �ö� �� ȿ���� �߰�
* ���� ���� �� ����Ʈ�� ȿ���� �߰�

# �ڵ� ����
Main.py, game.py, blocks.py 3���� ���Ϸ� ����.

colors = {1: (255, 0, 0), 2: (0, 255, 0), 3: (0, 0, 255), 4: (255, 255, 0),
    5: (0, 255, 255), 6: (255, 0, 255), 7: (255,255,255)}

��ϵ��� ������ ������ �����ص�.

    while tetris.game: ##���� ���� / ȭ�� ǥ��
        newTick = pygame.time.get_ticks()
        diff = newTick - lastTick
        lastTick = newTick
        update(diff)

        screen.fill((0, 0, 0))

        drawBlock(screen, rect, tetris.getGameMap(), (0, 0))
        drawBlock(screen, rect, tetris.getBlock().getRotatedShape(),tetris.getPosition())
        drawBlock(screen, rect, tetris.getnextBlock().getnextblock(), (25,3))
        pygame.draw.rect(screen, (50, 50, 50), (375, 0, 150, 500))
        screen.blit(text_nextblock, textrect_nextblock)
        screen.blit(text_stage,textrect_stage)
        screen.blit(text_score, textrect_score)
        text_stage_ = font2.render(str(tetris.stage), True, (200, 0, 0))
        screen.blit(text_stage_, textrect_stage_)
        text_score_ = font2.render(str(tetris.score), True, (100, 100, 100))
        screen.blit(text_score_, textrect_score_)
def drawBlock(screen, rect, shape, position): #��� �׸���
    for y in range(len(shape)):
        for x in range(len(shape[0])):
            if not shape[y][x] == 0:
                rect.fill(colors[shape[y][x]])
                screen.blit(rect, (25 * (x + position[0]), 25 * (y + position[1])))
main���� while���� ���
Screen.fill�� screen�� ��� ��� ��
Screen.blit���� screen������ ��ϵ�� �ؽ�Ʈ�� �׸���
Pygame.display.flip���� screen�� ������� ���� â�� ǥ����.

def __initGameMap(self):
    gameMap = []
    for y in range(20):
        gameMap.append([])
        for x in range(15):
            gameMap[y].append(0)
    return gameMap

��Ʈ���� ���� 20*15�� �迭�� ����.(���� 15 ���� 20)

class Block1(Block):
    
    def __init__(self):
        Block.__init__(self, [[0,0,0,0],
                              [0,1,1,0],
                              [1,1,0,0],
                              [0,0,0,0]], 1)
class Block7(Block):
    def __init__(self):
        Block.__init__(self, [[0, 0, 0, 0],
                              [0, 7, 0, 0],
                              [7, 7, 7, 0],
                              [0, 0, 0, 0]], 7)

��� �������� class�� ����� 4*4 �迭�� ����� ����� ����.
0 : ������(��ĭ) 
1~7 : ��� ������ ����

def __addBlockToGameMap(self):
    for y in range(self._currentBlock.getHeight()):
        for x in range(self._currentBlock.getWidth()):
            value = self._currentBlock.getRotatedShape()[y][x]
            if value is 0:
                continue
            y2 = self._position[1] + y
            x2 = self._position[0] + x
            self._gameMap[y2][x2] = value

����� 4*4 �迭�� ��� ���� ��Ʈ���� �� �迭�� ���� �ٲ� ����� �߰���.

def __doesBlockCollide(self):
    for y in range(self._currentBlock.getHeight()):
        for x in range(self._currentBlock.getWidth()):
            shapeValue = self._currentBlock.getRotatedShape()[y][x]
            y2 = self._position[1] + y
            x2 = self._position[0] + x
            if(x2<=-1 or x2>=15 or y2>=20):
                mapValue = 0
            else :
                mapValue = self._gameMap[y2][x2]
            if shapeValue and mapValue:
                return True
    return False
def __doesBlockCollidewithwall(self):
    for y in range(self._currentBlock.getHeight()):
        for x in range(self._currentBlock.getWidth()):
            shapeValue = self._currentBlock.getRotatedShape()[y][x]
            y2 = self._position[1] + y
            x2 = self._position[0] + x
            if shapeValue and (x2<=-1 or x2>=15 or y2>=20):
                return True
    return False

����� �ٸ� ����̳� ���� ��Ҵ��� �˻��ϴ� �Լ��� ����.


def lineclear(self,line):
    for x in range(0,15):
        self._gameMap[line][x] = 0
    for y in range(0,line):
        for z in range(0,15):
            self._gameMap[line-y][z] = self._gameMap[line-y-1][z]
def linesclear(self):
    for y in range(0,20):
        x = 0
        while x<15:
            if self._gameMap[y][x] == 0:
                break
            x += 1
        if x == 15:
            self.fire_state[y] = 1
            self.lineclear(y)
            self.score += 100

�� ���� �� á���� �˻��ϰ� �� �� ���� 0���� �ٲ� ����� �����ϴ� �Լ��� ����.

def rotate(self):
    self._currentBlock.rotateCounterclockwise()
    xmove = 0
    ymove = 0
    for y in range(self._currentBlock.getHeight()):
        for x in range(self._currentBlock.getWidth()):
            shapeValue = self._currentBlock.getRotatedShape()[y][x]
            y2 = self._position[1] + y
            x2 = self._position[0] + x
            if shapeValue and x2<=-1:
                xmove += 1
            if shapeValue and x2>=15:
                xmove -= 1
            if shapeValue and y2>=20:
                ymove -= 1
    self._position[0] += xmove
    self._position[1] += ymove

����� ȸ����Ű�� ���� ������ ����� �о�� �Լ��� ����.

def getRotatedShape(self):
    rotatedShape = self._shape
    for n in range(int(self._angle / 90)):
        rotatedShape = list(zip(*rotatedShape))[::-1]
    return rotatedShape

ȸ���� ����� ��� ����� �������ִ� �Լ��� ����.

for x in range(len(tetris.fire_state)):
    makefire = drawfire(screen,x)
    next(makefire)
    if(tetris.fire_state[x]==1):
        makefire.send(fire_1)
    elif(tetris.fire_state[x]==2):
        makefire.send(fire_2)
    elif (tetris.fire_state[x] == 3):
        makefire.send(fire_3)
    elif (tetris.fire_state[x] == 4):
        makefire.send(fire_4)
    elif (tetris.fire_state[x] == 5):
        makefire.send(fire_5)
    elif (tetris.fire_state[x] == 6):
        makefire.send(fire_6)
pygame.display.flip()

def drawfire(screen,line):
    while(True):
        state = yield
        screen.blit(state,(0,line*25))
        tetris.fire_state[line] += 1
        if(tetris.fire_state[line] == 7):
            tetris.fire_state[line] = 0
        pygame.mixer.Sound.play(clearsound)
        time.sleep(0.02)

����� ������ �� ����Ʈ�� �߰��ϴ� �Լ��� ����� �ڷ�ƾ�� ����Ͽ� ����Ʈ�� ǥ�õǴ� ���ȿ��� ������ ������ �ʰ� ����.

if(tetris.score < 1000):
    os.startfile('main.py')

score 1000�� �޼����� ���ϰ� ���ӿ��� �й��ϸ� ������ �ٽ� �����Ŵ.

if(tetris.stageup):
    pygame.mixer.music.stop()
    tetris.stageup = False
    if(tetris.stage == 3):
        pygame.mixer.Sound.play(speedup_)
        screen.fill((0, 0, 0))
        screen.blit(speedup, (0, 0))
        pygame.display.flip()
    elif(tetris.stage == 2):
        pygame.mixer.Sound.play(stageup_)
        screen.fill((0, 0, 0))
        screen.blit(stageup2,(0,0))
        pygame.display.flip()
    elif (tetris.stage == 5):
        pygame.mixer.Sound.play(stageup4)
        screen.fill((0, 0, 0))
        screen.blit(stageup4_, (0, 0))
        pygame.display.flip()
        justice = 50
    elif (tetris.stage == 4):
        pygame.mixer.Sound.play(stageup_)
        screen.fill((0, 0, 0))
        screen.blit(stageup3, (0, 0))
        pygame.display.flip()
    else:
        pygame.mixer.Sound.play(stageup_)
        screen.fill((0, 0, 0))
        screen.blit(stageup, (0, 0))
        pygame.display.flip()
    time.sleep(2)
    pygame.mixer.music.play(-1, 0.0)

stage�� �ö󰡸� Ư�� ȿ���� ����� ���.

for event in pygame.event.get():
    if event.type == pygame.QUIT:
        pygame.mixer.music.stop()
        pygame.mixer.music.load('bgm2.mp3')
        pygame.mixer.music.play()
        screen.fill((0,0,0))
        screen.blit(jigsaw_1,(500,400))
        pygame.display.flip()
        time.sleep(1)
        screen.fill((0, 0, 0))
        screen.blit(jigsaw_2,(100,100))
        pygame.display.flip()
        time.sleep(1)
        screen.fill((0, 0, 0))
        screen.blit(jigsaw_3,(400,100))
        pygame.display.flip()
        time.sleep(1)
        screen.fill((0, 0, 0))
        screen.blit(jigsaw_4,(100,300))
        pygame.display.flip()
        time.sleep(1)
        screen.fill((0, 0, 0))
        screen.blit(jigsaw_5,(300,200))
        pygame.display.flip()
        time.sleep(1)
        screen.fill((0, 0, 0))
        screen.blit(jigsaw,(0,0))
        pygame.display.flip()
        time.sleep(1)
        screen.fill((0, 0, 0))
        pygame.display.flip()
        time.sleep(1)
        pygame.display.set_caption('�ڡ١ڡ١ڡ٢�������� ���� �� ����С١ڡ١ڡ١� ���ᰡ�� �Ѣ�http://kr.battle.net/heroes/ko/�Т�')
        pygame.mixer.music.stop()
        pygame.mixer.music.load('storm.mp3')
        pygame.mixer.music.play(1, 0.0)
        accel = 0.01
        for x in range(0, 720):
            screen.fill((0, 0, 100))
            storm = pygame.transform.rotate(storm_, x * accel)
            screen.blit(storm, (400 - storm.get_rect().width / 2, 250 - storm.get_rect().height / 2))
            pygame.display.flip()
            accel += 0.01
        screen.fill((0, 0, 100))
        screen.blit(hots,(0,0))
        pygame.display.flip()
        time.sleep(1)
        webbrowser.open(url)
        if(os.path.exists('C:\Program Files (x86)\Blizzard App\Battle.net Launcher.exe')):
            os.startfile('C:\Program Files (x86)\Blizzard App\Battle.net Launcher.exe')
        sys.exit()

������ �����ϸ� ȿ������ �����Ű�� �̹����� ��� �� �̹����� ȸ����Ű�ٰ� webbrowser.open���� ��������� ���� �� ���衯 Ȩ�������� ���� ������� ��ǻ�Ϳ� Battle.net ���� �⺻ ��ο� ��ġ�Ǿ� ������ Battle.net ���� �����Ŵ.

