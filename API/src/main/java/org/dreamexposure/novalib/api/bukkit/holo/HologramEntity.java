package org.dreamexposure.novalib.api.bukkit.holo;

import org.bukkit.Location;
import org.dreamexposure.novalib.api.bukkit.serialization.JsonSerializer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author NovaFox161
 * Date Created: 10/19/2018
 * For Project: NovaLib
 * Author Website: https://www.novamaday.com
 * Company Website: https://www.dreamexposure.org
 * Contact: nova@dreamexposure.org
 */
@SuppressWarnings("WeakerAccess")
public class HologramEntity {
    private final String name;
    private Location location;
    private List<String> lines;

    public HologramEntity(String _name, Location _location, List<String> _lines) {
        name = _name;
        location = _location;
        lines = _lines;
    }

    public HologramEntity(String _name, Location _location, String _line) {
        this(_name, _location, Collections.singletonList(_line));
    }

    public HologramEntity(JSONObject json) {
        name = json.getString("name");
        location = JsonSerializer.deserializeLocation(json.getJSONObject("location"));
        JSONArray jLines = json.getJSONArray("lines");
        lines = new ArrayList<>();
        for (int i = 0; i < jLines.length(); i++) {
            lines.add(jLines.getString(i));
        }
    }

    //Getters
    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public ArrayList<String> getLines() {
        return new ArrayList<>(lines);
    }


    //Setters
    public void setLocation(Location _location) {
        location = _location;
    }

    //Functions
    public void addLine(String _string) {
        lines.add(_string);
    }

    public void removeLine(int _line) {
        lines.remove(_line);
    }

    public void removeLine(String _string) {
        lines.remove(_string);
    }

    public JSONObject toJson() {
        JSONObject holo = new JSONObject();
        holo.put("name", name);
        holo.put("location", JsonSerializer.serializeLocation(location));
        holo.put("lines", lines);

        return holo;
    }
}