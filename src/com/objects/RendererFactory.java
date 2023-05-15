package com.objects;

import API.Renderer;

public class RendererFactory {

    public Renderer buildRenderer(String renderer){
        return switch (renderer) {
            case "console" -> new ConsoleRenderer();
            case "none" -> new VoidRenderer();
            default -> null;
        };

    }
}
