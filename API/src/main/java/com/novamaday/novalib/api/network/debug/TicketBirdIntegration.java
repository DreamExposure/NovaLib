package com.novamaday.novalib.api.network.debug;

import com.novamaday.novalib.api.network.hastebin.Uploader;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TicketBirdIntegration {
    private final String projectName;
    private final long guildId;

    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client;

    /**
     * Creates a new Integration with TicketBird for the specified project and will send the info the the guild provided.
     *
     * @param _projectName The name of the project.
     * @param _guildId     The guild to send the ticket to.
     */
    public TicketBirdIntegration(String _projectName, long _guildId) {
        projectName = _projectName;
        guildId = _guildId;

        client = new OkHttpClient();
    }

    /**
     * Gets the name of the project
     *
     * @return The name of the project.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Gets the ID of the guild tickets are sent to.
     *
     * @return the ID of the guild tickets are sent to.
     */
    public long getGuildId() {
        return guildId;
    }

    /**
     * Opens a new error report ticket with TicketBird for the guild and project.
     *
     * @param e                 The exception causing this error report.
     * @param additionalDetails Any additional details needed (actions causing the error, timestamp, etc)
     * @return <code>true</code> if successful, otherwise <code>false</code>
     */
    public boolean openErrorReport(Exception e, String additionalDetails) {
        JSONObject json = new JSONObject();

        json.put("project", projectName);
        json.put("reason", "!= Auto generated Error report by NovaLib =!");
        json.put("guild-id", guildId);

        json.put("description", additionalDetails);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String error = sw.toString(); // stack trace as a string
        pw.close();
        try {
            sw.close();
        } catch (IOException e1) {
            //Can ignore silently...
        }

        json.put("link", Uploader.post(error));

        RequestBody body = RequestBody.create(JSON, json.toString());

        String apiURL = "https://ticketbird.novamaday.com/api/v1/ticket/create";
        Request request = new Request.Builder()
                //This can be public, its authorization for TicketBird's API only able to be used by NovaLib.
                .header("Authorization", "CXikHAAARAIiiw2EnjbEX3E8ERammFJurMwqOOCJpv8qOge54qkr2mSQ69s9TznH")
                .url(apiURL)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.code() == 200;
        } catch (IOException e1) {
            System.out.println("[NovaLib] Failed to send error report to TicketBird!");
            e1.printStackTrace();
        }
        return false;
    }
}