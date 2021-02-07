# 🦝 mini-animal-crossing
<br>
<img src="../master/gameImg/main.PNG" width="50%" height="50%">

**Java/Swing을 이용해 제작한 미니 동물의 숲 게임**
- **개발 기간: 2021/01/11 ~ 2021/01/18**
- **개발 환경: IntelliJ IDEA Communicate Edition**
- **실행 영상: https://youtu.be/V3fBOO8czgM**
<br>

| 캐릭터 | 특성 | 캐릭터 | 특성 |
|:-:| :-| :-: | :-|
| <img src="../master/gameImg/player.png"> | 총 1000벨을 너굴이에게 갚아야 하는 게임의 주인공.<br>주민들과 적당히 교류하며 벨을 모아야 한다. | <img src="../master/gameImg/neogull.png"> | 한 번 만날 때 500벨 이상 가지고 있지 않으면 퇴짜를 놓는 너굴. |
| <img src="../master/gameImg/jjuny.png"> | 방해도 하지 않고, 도움도 주지 않고, 느끼하기만 한 쭈니. | <img src="../master/gameImg/bboyami.png"> | 랜덤으로 주인공에게 10~100벨을 제공하는 뽀야미. |
| <img src="../master/gameImg/apolo.png"> | 주인공이 200벨 이상을 가지고 있으면 200벨을 가져가 버리는 아폴로. | <img src="../master/gameImg/coin.png"> | 한 번 주우면 100벨이 생긴다.|

<br>

```
- keyPressed: W(↑), A(←), S(↓), D(→) 눌렀을시 해당 방향으로 이동
- keyReleased: W(↑), A(←), S(↓), D(→) 눌렀다가 뗐을 시 이동 중지
- Init: 캐릭터와 동전 위치 랜덤 배치, 점수 초기화
- crashCheck: 주인공과 동전 충돌시 점수 증가
- meetBboyami, meetJjuny, meetApolo, meetNeogull: 캐릭터와 충돌시 이벤트 발생
- isClear: 목표 달성 여부 확인
- playSound: 배경음악 설정
- screenDraw: 배경이미지, 캐릭터 등 이미지 배치
```
