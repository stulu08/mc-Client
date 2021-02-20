package stulu.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import stulu.Client;

public class CosmeticController {
    public static boolean render;
    public static boolean checked = false;
    public static void setChecked(boolean value){checked = value;};
    public static void checkUser(AbstractClientPlayer player) {
            for (int i = 0; i != Client.ClientUsers.length; i++) {
                if (player.getGameProfile().getId().toString().equals(Client.ClientUsers[i])) {
                    render = true;
                    return;
                }
            }
            render = false;
    }


    //Hats
    public static boolean shouldRenderTopHat(AbstractClientPlayer player){
        if(Client.CurrentHat == 0){
            if(!checked){
                checkUser(player);
            }
            return render;
        }
        else{
            return false;
        }
    }
    public static float[] getTopHatColor(AbstractClientPlayer player){
        return new float[]{1,0,0,1};
    }
    //Capes
    public static boolean shouldRenderCape(AbstractClientPlayer player){
        if(!checked){
            checkUser(player);
        }
        return render;
    }
    //Wings
    public static boolean shouldRenderDragonWings(AbstractClientPlayer player){
        if(Client.EnableDragonWings) {
            if(!checked){
                checkUser(player);
            }
            return render;
        }
        else{
            return false;
        }
    }
    public static float[] getDragonWingsColor = new float[]{1f,1f,1f,1f};
    public static void setDragonWingsColor(float[] color){
        if(color.length != 4)
            color = new float[]{1,1,1,1};
        getDragonWingsColor = new float[]{color[0], color[1], color[2], color[3]};
        Client.Sfile.ClientINIWrite.save("Cosmetics","D-wings-color",color[0] + "," +color[1] + ","+color[2] + ","+color[3]);
    }
    //Blaze
    public static boolean shouldRenderBlazeEffect(AbstractClientPlayer player){
        if(Client.BlazeEffect) {
            if(!checked){
                checkUser(player);
            }
            return render;
        }
        else{
            return false;
        }
    }
    public static float[] getBlazeEffectColor = new float[]{1f,1f,1f,1f};
    public static void setGetBlazeEffectColor(float[] color){
        if(color.length != 4)
            color = new float[]{1,1,1,1};
        getBlazeEffectColor = new float[]{color[0], color[1], color[2], color[3]};
        Client.Sfile.ClientINIWrite.save("Cosmetics","blaze-color",color[0] + "," +color[1] + ","+color[2] + ","+color[3]);
    }
    public static void init(){
        try{
        String s = Client.Sfile.ClientINIRead.get("Cosmetics","D-wings-color");
        String[] v = {"1,1,1,1"};
        if(s != null){
            v = s.split(",");
            getDragonWingsColor = new float[]{Float.parseFloat(v[0]),Float.parseFloat(v[1]),Float.parseFloat(v[2]),Float.parseFloat(v[3])};
        }

        if(s != null){
            s = Client.Sfile.ClientINIRead.get("Cosmetics","blaze-color");
            v = s.split(",");
        }

        getBlazeEffectColor = new float[]{Float.parseFloat(v[0]),Float.parseFloat(v[1]),Float.parseFloat(v[2]),Float.parseFloat(v[3])};
        }catch (Exception exception){
            Client.Sfile.ClientINIWrite.save("Cosmetics","D-wings-color","1,1,1,1");
            Client.Sfile.ClientINIWrite.save("Cosmetics","blaze-color","1,1,1,1");
        }
    }

}
