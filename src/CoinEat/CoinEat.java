package CoinEat;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class CoinEat extends JFrame {

    private Image bufferImage;
    private Graphics screenGraphic;
    private Clip clip;

    private Image backgroundImage = new ImageIcon("src/images/background.png").getImage();
    private Image key = new ImageIcon("src/images/key.png").getImage();
    private Image earnedImg = new ImageIcon("src/images/earnedImg.png").getImage();
    private Image payedImg = new ImageIcon("src/images/payedImg.png").getImage();
    private Image player = new ImageIcon("src/images/player.png").getImage();
    private Image coin = new ImageIcon("src/images/coin.png").getImage();
    private Image neogull = new ImageIcon("src/images/neogull.png").getImage();
    private Image jjuny = new ImageIcon("src/images/jjuny.png").getImage();
    private Image jjunyTalk = new ImageIcon("src/images/jjunyTalk.png").getImage();
    private Image apolo = new ImageIcon("src/images/apolo.png").getImage();
    private Image apoloTalk = new ImageIcon("src/images/apoloTalk.png").getImage();
    private Image bboyami = new ImageIcon("src/images/bboyami.png").getImage();
    private Image bboyamiTalk = new ImageIcon("src/images/bboyamiTalk.png").getImage();
    private Image angry = new ImageIcon("src/images/angry.png").getImage();
    private Image finished = new ImageIcon("src/images/finished.png").getImage();
    private Image title = new ImageIcon("src/images/title.png").getImage();

    private int playerX, playerY;
    private int playerWidth = player.getWidth(null);
    private int playerHeight = player.getHeight(null);

    private int coinX, coinY;
    private int coinWidth = coin.getWidth(null);
    private int coinHeight = coin.getHeight(null);

    private int neogullX, neogullY;
    private int neogullWidth = neogull.getWidth(null);
    private int neogullHeight = neogull.getHeight(null);

    private int jjunyX, jjunyY;
    private int jjunyWidth = jjuny.getWidth(null);
    private int jjunyHeight = jjuny.getHeight(null);

    private int jjunyTalkX, jjunyTalkY;
    private int jjunyTalkWidth = jjunyTalk.getWidth(null);
    private int jjunyTalkHeight = jjunyTalk.getHeight(null);

    private int apoloX, apoloY;
    private int apoloWidth = apolo.getWidth(null);
    private int apoloHeight = apolo.getHeight(null);

    private int apoloTalkX, apoloTalkY;
    private int apoloTalkWidth = apoloTalk.getWidth(null);
    private int apoloTalkHeight = apoloTalk.getHeight(null);

    private int bboyamiX, bboyamiY;
    private int bboyamiWidth = bboyami.getWidth(null);
    private int bboyamiHeight = bboyami.getHeight(null);

    private int bboyamiTalkX, bboyamiTalkY;
    private int bboyamiTalkWidth = bboyamiTalk.getWidth(null);
    private int bboyamiTalkHeight = bboyamiTalk.getHeight(null);

    private int angryX, angryY;
    private int angryWidth = angry.getWidth(null);
    private int angryHeight = angry.getHeight(null);

    private int finishedX, finishedY;
    private int finishedWidth = finished.getWidth(null);
    private int finishedHeight = finished.getHeight(null);

    private int titleWidth = title.getWidth(null);
    private int titleHeight = title.getHeight(null);

    private int score;
    private int payed;

    private boolean up, down, left, right;

    public CoinEat() {
        setTitle("동전 먹기 게임");
        setVisible(true);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter() {
           public void keyPressed(KeyEvent e) {
               switch(e.getKeyCode()) {
                   case KeyEvent.VK_W:
                    up = true;
                    break;
                   case KeyEvent.VK_S:
                       down = true;
                       break;
                   case KeyEvent.VK_A:
                       left = true;
                       break;
                   case KeyEvent.VK_D:
                       right = true;
                       break;
               }
           }
            public void keyReleased(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        up = false;
                        break;
                    case KeyEvent.VK_S:
                        down = false;
                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        break;
                }
            }
        });
        Init();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            keyProcess();
            crashCheck();
            meetBboyami();
            meetJjuny();
            meetApolo();
            meetNeogull();
            isClear();
        }
    }

    public void Init() {
        score = 0;
        payed = 0;
        playerX = (1000 - playerWidth) / 2;
        playerY = (700 - playerHeight) / 2;
        coinX = (int)(Math.random()*(1001-coinWidth));
        coinY = (int)(Math.random()*(701-playerHeight-30))+30;
        neogullX = (int)(Math.random()*(1001-playerWidth));
        neogullY = (int)(Math.random()*(701-playerHeight-30))+30;
        jjunyX = (int)(Math.random()*(1001-playerWidth));
        jjunyY = (int)(Math.random()*(701-playerHeight-30))+30;
        jjunyTalkX = 1000;
        jjunyTalkY = 1000;
        bboyamiX = (int)(Math.random()*(1001-playerWidth));
        bboyamiY = (int)(Math.random()*(701-playerHeight-30))+30;
        bboyamiTalkX = 1000;
        bboyamiTalkY = 1000;
        apoloX = (int)(Math.random()*(1001-playerWidth));
        apoloY = (int)(Math.random()*(701-playerHeight-30))+30;
        apoloTalkX = 1000;
        apoloTalkY = 1000;
        angryX = 1000;
        angryY = 1000;
        finishedX = 1000;
        finishedY = 1000;

        playSound("src/audio/bgm.wav", true);

    }

    public void keyProcess() {
        if (up && playerY - 3 > 30) playerY -= 3;
        if (down && playerY + playerHeight + 3 < 700) playerY += 3;
        if (left && playerX - 3 > 0) playerX -= 3;
        if (right && playerX + playerWidth + 3 < 1000) playerX += 3;
    }

    public void crashCheck() {
        if (playerX + playerWidth > coinX && coinX + coinWidth > playerX && playerY + playerHeight > coinY && coinY + coinHeight > playerY) {
            score += 100;
            playSound("src/audio/getCoin.wav", false);
            coinX = (int)(Math.random()*(1001-playerWidth));
            coinY = (int)(Math.random()*(701-playerHeight-30))+30;
        }
    }

    public void meetBboyami() {
        if (playerX + playerWidth > bboyamiX && bboyamiX + bboyamiWidth > playerX && playerY + playerHeight > bboyamiY && bboyamiY + bboyamiHeight > playerY) {
            bboyamiTalkX = (1000- jjunyTalkWidth) / 2;
            bboyamiTalkY = 500;
            score += (int)(Math.random()*10 + 1) * 10;
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            bboyamiX = (int)(Math.random()*(1001-playerWidth));
            bboyamiY = (int)(Math.random()*(701-playerHeight-30))+30;
            bboyamiTalkX = 1000;
            bboyamiTalkY = 1000;
        }
    }

    public void meetJjuny() {
        if (playerX + playerWidth > jjunyX && jjunyX + jjunyWidth > playerX && playerY + playerHeight > jjunyY && jjunyY + jjunyHeight > playerY) {
            jjunyTalkX = (1000- jjunyTalkWidth) / 2;
            jjunyTalkY = 500;
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            jjunyX = (int)(Math.random()*(1001-playerWidth));
            jjunyY = (int)(Math.random()*(701-playerHeight-30))+30;
            jjunyTalkX = 1000;
            jjunyTalkY = 1000;
        }
    }

    public void meetApolo() {
        if (playerX + playerWidth > apoloX && apoloX + apoloWidth > playerX && playerY + playerHeight > apoloY && apoloY + apoloHeight > playerY) {
            if (score >= 200) {
                apoloTalkX = (1000 - apoloTalkWidth) / 2;
                apoloTalkY = 500;
                score -= 200;
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            apoloX = (int)(Math.random()*(1001-playerWidth));
            apoloY = (int)(Math.random()*(701-playerHeight-30))+30;
            apoloTalkX = 1000;
            apoloTalkY = 1000;
        }
    }

    public void meetNeogull() {
        if (playerX + playerWidth > neogullX && neogullX + neogullWidth > playerX && playerY + playerHeight > neogullY && neogullY + neogullHeight > playerY) {
            if (score < 500) {
                angryX = (1000 - angryWidth) / 2;
                angryY = 500;
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            else if (score >= 500) {
                score -= 500;
                payed += 500;
            }
            neogullX = (int)(Math.random()*(1001-playerWidth));
            neogullY = (int)(Math.random()*(701-playerHeight-30))+30;
            angryX = 1000;
            angryY = 1000;
        }
    }

    public void isClear() {
        if (payed >= 1000) {
            finishedX = (1000 - finishedWidth) / 2;
            finishedY = 500;
        }
    }

    public void playSound(String pathName, boolean isLoop) {
        try {
            clip = AudioSystem.getClip();
            File audioFile = new File(pathName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip.open(audioStream);
            clip.start();
            if (isLoop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        bufferImage = createImage(1000, 700);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage, 0, 0, null);
    }

    public void screenDraw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(key, 30, 550, null);
        g.drawImage(player, playerX, playerY, null);
        g.drawImage(coin, coinX, coinY, null);
        g.drawImage(neogull, neogullX, neogullY, null);
        g.drawImage(jjuny, jjunyX, jjunyY, null);
        g.drawImage(apolo, apoloX, apoloY, null);
        g.drawImage(bboyami, bboyamiX, bboyamiY, null);
        g.drawImage(title, (1000 - titleWidth) / 2, 40, null);
        g.drawImage(jjunyTalk, jjunyTalkX, jjunyTalkY, null);
        g.drawImage(apoloTalk, apoloTalkX, apoloTalkY, null);
        g.drawImage(bboyamiTalk, bboyamiTalkX, bboyamiTalkY, null);
        g.drawImage(angry, angryX, angryY, null);
        g.drawImage(finished, finishedX, finishedY, null);
        g.drawImage(earnedImg, 50, 35, null);
        g.drawImage(payedImg, 800, 43, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString(score + "", 110, 120);
        g.drawString(payed + "", 820, 125);
        g.drawString("/ 1000", 875, 125);

        this.repaint();
    }

    public static void main(String[] args) {
        new CoinEat();
    }

}
