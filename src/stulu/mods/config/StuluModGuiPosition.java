package stulu.mods.config;

public class StuluModGuiPosition {

    public StuluModGuiPosition(double _posX, double _posY){
        setPosX(_posX);
        setPosY(_posY);
    }
    double posX;
    double posY;
    public void setPosX(double _x){
        posX = _x;
    }
    public void setPosY(double _y){
        posY = _y;
    }
    public int getPosX() {
        return (int)posX;
    }
    public int getPosY() {
        return (int)posY;
    }

    @Override
    public String toString() {
        return getPosX() + "," + getPosY();
    }
}
