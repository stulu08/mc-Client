package stulu.mods;

import stulu.mods.config.IStuluModConfig;

public abstract class StuluMod {
    public void onEnable(){

    }
    public void onDisable(){

    }
    public void onUpdate(){

    }
    public IStuluModConfig config(){
      return null;
    }
}
