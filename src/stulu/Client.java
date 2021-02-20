package stulu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import stulu.commands.CommandInstances;
import stulu.commands.StuluCommandApi;
import stulu.cosmetics.CosmeticController;
import stulu.events.EventManager;
import stulu.events.EventTarget;
import stulu.events.impl.StuluGameExitEvent;
import stulu.events.impl.StuluRenderEvent;
import stulu.events.impl.StuluTickEvent;
import stulu.file.StuluFile;
import stulu.gui.Crossair;
import stulu.gui.screens.SplashProgress;
import stulu.mods.StuluGUIModinstances;
import stulu.mods.StuluRenderApi;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private static final Client INSTANCE = new Client();
    public static final Client getINSTANCE(){
        return INSTANCE;
    }
    public static final Client g(){
        return INSTANCE;
    }
    public static Crossair SCrossair = new Crossair();
    private final DiscordRP discordRP = new DiscordRP();
    StuluRenderApi renderApi;
    StuluCommandApi commandApi;

    public void init(){
        SplashProgress.setProgress(1, "Stulu - Initializing Stulu Client");
        discordRP.Start();

        EventManager.register(this);
        initGUIStuff();

        try{
            Sfile = new StuluFile("client");
        } catch (IOException exception){
            System.out.println(exception.toString());
        }
        CosmeticController.init();
        syncCosmetics();
        SCrossair.init();
    }
    public static StuluFile Sfile;
    public void start(){
        renderApi = StuluRenderApi.getInstance();
        commandApi = StuluCommandApi.getInstance();
        StuluGUIModinstances.Register(renderApi);
        CommandInstances.register(commandApi);
        StuluGUIModinstances.init(renderApi);
        initColorVars();
        GuiScreen.registerStuluCommandApi(commandApi);

        System.out.print(Minecraft.getMinecraft().getSession().getUsername());
        CosmeticController.setChecked(false);
    }
    @EventTarget
    public void onTick(StuluTickEvent e){
        if(Minecraft.getMinecraft().gameSettings.STULU_GUI_MODS_INGAME.isPressed())
            renderApi.openConfigScreen();
    }

    public void shutDown(){
        discordRP.shutDown();
    }
    public DiscordRP getDiscordRP(){
        return this.discordRP;
    }


    public static String[] ClientUsers;

    public String getVersion(){
        return Sfile.ClientINIRead.get("Stulu","Version");
    }
    ////////////////////////cosmetic stuff
    public static List<String> ReadWebFile(String Website, boolean WithSpaces){
        try {
            URL url = new URL(Website);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            List<String>names = new ArrayList<String>();

            while ((line = in.readLine()) != null) {
                if(WithSpaces)
                    names.add(line + "|");
                else
                    names.add(line);
            }
            in.close();
            for(int i=0;i!=names.size();i++){
                if(names.get(i).equals(Minecraft.getMinecraft().getSession().getUsername()))
                    names.remove(i);
            }
            names.add(Minecraft.getMinecraft().getSession().getUsername());
            return names;

        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
            List<String> rte = new ArrayList<String>();
            rte.add("stuluclienttest");
            rte.add("stulu");
            rte.add(Minecraft.getMinecraft().getSession().getUsername());
            return rte;
            }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
            List<String> rte = new ArrayList<String>();
            rte.add("stuluclienttest");
            rte.add("stulu");
            rte.add(Minecraft.getMinecraft().getSession().getUsername());
            return rte;
        }
    }
    public void syncCosmetics(){
        CurrentHat = Integer.parseInt(Sfile.ClientINIRead.get("Cosmetics","hat"));
        CurrentCape = Integer.parseInt(Sfile.ClientINIRead.get("Cosmetics","cape"));

        Glow = Sfile.ClientINIRead.getBool("Cosmetics","glow");
        EnableDragonWings = Sfile.ClientINIRead.getBool("Cosmetics","D-wings");
        BlazeEffect = Sfile.ClientINIRead.getBool("Cosmetics","blaze");
    }
    //////////Main Cosmetics//////////
    //Hats
    public static String[] Hats = new String[]{
            "magic","none"
    };
    public static int CurrentHat = 1;
    public static void AddCurrentHat(){CurrentHat++;Sfile.ClientINIWrite.save("Cosmetics","hat","" + CurrentHat);}
    public static void SetCurrentHat(Integer Amount){CurrentHat=Amount; Sfile.ClientINIWrite.save("Cosmetics","hat",Amount.toString());}
    //Wings
    public static boolean EnableDragonWings = true;
    public static void setEnableDragonWings(boolean Value){EnableDragonWings = Value;Sfile.ClientINIWrite.save("Cosmetics","D-wings",""+Value);}
    //Capes
    public static int CurrentCape = 9;
    public static void AddCurrentCape(){CurrentCape++;Sfile.ClientINIWrite.save("Cosmetics","cape",""+CurrentCape);}
    public static void SetCurrentCape(int Amount){CurrentCape=Amount;Sfile.ClientINIWrite.save("Cosmetics","cape",""+ CurrentCape);}
    public static String[] Capes = new String[]{
            "2011","2012","2013","2015","2016","2019","turtle","mojang","custom","none"
    };
    /////////Effects//////////////
    //Blaze
    public static void setBlazeEffect(boolean Value){BlazeEffect=Value;Sfile.ClientINIWrite.save("Cosmetics","blaze",""+Value);}
    public static boolean BlazeEffect = true;
    //Glow
    public static boolean Glow = false;
    public static void setGlow(boolean glow) {
        Glow = glow;
        Sfile.ClientINIWrite.save("Cosmetics","glow",""+Glow);
    }

    private void initGUIStuff(){
        ClientUsers = ReadWebFile("https://raw.githubusercontent.com/stulu08/Applications/main/mc-client/Data/ClientUsers",false).toArray(new String[0]);

        System.out.println("[Stulu][ClientData]: Registered Premium users("+ClientUsers.length+"):");
        for(int i=0; i!=ClientUsers.length;i++){
            System.out.print(ClientUsers[i] + " | ");
            if((i % 5) == 0 && i > 4){
                System.out.print("\n");
            }
        }
        System.out.println("\n[Stulu][Client]: Created by " +ClientUsers[0]);
    }
    public Color Rainbow;
    private float h = 0;
    private float hSpeed = 1f;
    public void setHueSpeed(float value){
        hSpeed = value;
        Sfile.ClientINIWrite.save("UI","HueSpeed",""+value);
    }
    public void setHue(float value){
        h = value/360f;
        Sfile.ClientINIWrite.save("UI","UIColorHue",""+value);
    }
    public float gethSpeed() {
        return hSpeed;
    }
    public boolean isRainbow = true;
    public void setRainbow(boolean rainbow) {
        isRainbow = rainbow;
        Sfile.ClientINIWrite.save("UI","Rainbow",""+rainbow);
    }

    public float getHue() {
        return h;
    }

    void initColorVars(){
        setRainbow(Sfile.ClientINIRead.getBool("UI","Rainbow"));
        float v;
        String s = Sfile.ClientINIRead.get("UI","HueSpeed");
        v = Float.parseFloat(s);
        setHueSpeed(v);
        s = Sfile.ClientINIRead.get("UI","UIColorHue");
        v = Float.parseFloat(s);
        setHue(v);
    }
    @EventTarget
    public void onRender(StuluRenderEvent e){
        if(isRainbow) {
            Rainbow = Color.getHSBColor(h, 1f, 1f);

            if (h >= 1f) {
                h = 0f;
            } else {
                h += hSpeed / 360f;
            }
        }
        else{
            Rainbow = Color.getHSBColor(h,1f,1f);
        }
    }
    @EventTarget
    public void onGameExit(StuluGameExitEvent e){

    }
}