package com.campcomputer.journeyofthehairs;

import com.campcomputer.journeyofthehairs.panel.GamePanel;
import com.campcomputer.journeyofthehairs.panel.InstructionPanel;
import com.campcomputer.journeyofthehairs.panel.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class JourneyOfTheHairsFrame extends JFrame {
    static Timer t;
    public static int timerTick = 1000 / 30;
    private GameEngine engine;
    private final GamePanel gamePanel;
    private final MainMenuPanel mainMenuPanel;
    private final InstructionPanel instructionPanel;

    public JourneyOfTheHairsFrame() throws HeadlessException {
        super("Journey Of The Hairs");

        engine = new GameEngine();

        new BoxLayout(this, BoxLayout.X_AXIS);

        instructionPanel = new InstructionPanel();
        mainMenuPanel = new MainMenuPanel();
        gamePanel = new GamePanel(engine);

        add(instructionPanel);
        add(mainMenuPanel);
        add(gamePanel);

        setContentPane(gamePanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1024, 768);

        t = new Timer(timerTick, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                engine.tick();
                repaint();
                if (getContentPane() instanceof GamePanel) {

                } else if (getContentPane() instanceof MainMenuPanel) {

                } else if (getContentPane() instanceof InstructionPanel) {

                }
            }
        });
        showHelp();
        addGamePanelKeyListener();
    }

    private void addGamePanelKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        engine.startMoveBackward();
                        break;
                    case KeyEvent.VK_D:
                        engine.startMoveForward();
                        break;
                    case KeyEvent.VK_W:
                        engine.jump();
                        break;
                    case KeyEvent.VK_E:
                        gamePanel.toggleShowTiles();
                        break;
                    case KeyEvent.VK_Q:
                        
                        break;
                    case KeyEvent.VK_S:
                        engine.getPlayer().getWeapon().toggleShoot();
                        break;
                    case KeyEvent.VK_H:
                        showHelp();
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        engine.endMoveBackward();
                        break;
                    case KeyEvent.VK_D:
                        engine.endMoveForward();
                        break;
                    case KeyEvent.VK_S:
                        engine.getPlayer().getWeapon().toggleShoot();
                        break;
                }

            }
        });
    }

    private void showHelp() {
        t.stop();
        JOptionPane.showMessageDialog(this, "WAD = Movement, S = Shoot, E = Toggle Tiles");
        t.start();
    }
}