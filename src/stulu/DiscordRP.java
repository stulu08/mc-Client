package stulu;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {
    private boolean running = true;
    long created = 0;

    public void Start(){
        try {
            this.created = System.currentTimeMillis();
            DiscordEventHandlers handler = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
                @Override
                public void apply(DiscordUser discordUser) {
                    System.out.print("Websome " + discordUser.username + "#" + discordUser.discriminator);
                    Update("Starting...", "");
                }
            }).build();
            DiscordRPC.discordInitialize("744624162785460346", handler, true);

            new Thread("Discord RPC callback") {
                @Override
                public void run() {
                    while (running) {
                        DiscordRPC.discordRunCallbacks();
                    }
                }
            }.start();
        }
        catch(Exception exception){System.out.print("[Stulu][Client][DiscordRPC]: DiscordRPC crashed\n" + exception +"\n");}
    }
    public void shutDown(){
        running = false;
        DiscordRPC.discordShutdown();
    }
    public void Update(String firstLine, String secondLine){
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
        b.setBigImage("splashscreen_1000px","");
        b.setDetails(firstLine);
        //b.setEndTimestamp(created);
        b.setStartTimestamps(created);

        DiscordRPC.discordUpdatePresence(b.build());
    }
}
