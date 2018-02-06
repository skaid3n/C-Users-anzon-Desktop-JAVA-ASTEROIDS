package ASTEROIDS;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class asteroids extends Application {
    
//    int naveSpeedX= 0;
//    int naveSpeedY= 0;
    
    final int SCENE_TAM_X =800;
    final int SCENE_TAM_Y =600;
    
    int velGiroNave= 90;
    
    int naveCenterX= SCENE_TAM_X/2;
    int naveCenterY= SCENE_TAM_Y/2;
    
    int anguloVelNave;
    
    int anguloNave= 360;
    
    int naveVelocidadX;
    int naveVelocidadY;
    
    Pane root;
    
    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        
        Scene scene = new Scene(root, SCENE_TAM_X, SCENE_TAM_Y);
        scene.setFill(Color.WHITE);
        primaryStage.setTitle("ASTEROIDS PAVITA");
        primaryStage.setScene(scene);
        primaryStage.show();
        //nave
        Polygon nave = new Polygon();
        root.getChildren().add(nave);
        nave.setFill(Color.RED);
        nave.setTranslateX(naveCenterX);
        nave.setTranslateY(naveCenterY);
        nave.getPoints().addAll(new Double[]{
            0.0,  30.0,
            10.0, 0.0,
            20.0, 30.0 });
        
        AnimationTimer animationNave;
        animationNave = new AnimationTimer(){
            @Override
            public void handle(long now) {
                naveCenterX +=  naveVelocidadX;
                nave.setTranslateX(naveCenterX);
                
                naveCenterY +=  naveVelocidadY;
                nave.setTranslateY(naveCenterY);
                
                anguloNave += anguloVelNave;
                nave.setRotate(anguloNave);
                
                if (naveCenterX > SCENE_TAM_X){
                    naveCenterX = 0;
                }
                if (naveCenterX < 0){
                    naveCenterX = SCENE_TAM_X;
                } 
                if (naveCenterY > SCENE_TAM_Y){
                    naveCenterY = 0;
                }
                if (naveCenterY < 0){
                    naveCenterY = SCENE_TAM_Y;
                }
                if (anguloNave < 0){
                anguloNave = 360;
                }
                if (anguloNave > 360){
                anguloNave = 0;
                }
            }
            
        };
        animationNave.start();
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case D:
                   anguloVelNave = +5;
                   break;
                case A:
                   anguloVelNave = -5;
                   break;
                case W:
                   if(anguloNave < 45 || anguloNave > 315){
                        naveVelocidadY = -1;
                        naveVelocidadX = 0;
                    }
                    if(anguloNave < 135 && anguloNave > 45){
                        naveVelocidadX = +1;
                        naveVelocidadY = 0;
                    }
                    if(anguloNave < 225 && anguloNave > 135){
                        naveVelocidadY = +1;
                        naveVelocidadX = 0;
                    }
                    if(anguloNave < 315 && anguloNave > 225) {
                        naveVelocidadX = -1; 
                        naveVelocidadY = 0;
                    }
            }
            System.out.println(anguloNave);
        });
       
        scene.setOnKeyReleased((KeyEvent event) -> {          
            anguloVelNave= 0; 
        });
    }
}       
