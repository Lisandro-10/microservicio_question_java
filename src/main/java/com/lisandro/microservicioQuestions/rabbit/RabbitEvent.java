package com.lisandro.microservicioQuestions.rabbit;

import com.google.gson.annotations.SerializedName;
import com.lisandro.microservicioQuestions.utils.gson.GsonTools;

import jakarta.validation.constraints.NotEmpty;

public class RabbitEvent {
	 // tipo de mensaje enviado
    @SerializedName("type")
    @NotEmpty
    public String type;

    // Version del protocolo
    @SerializedName("version")
    public int version;

    // Por si el destinatario necesita saber de donde viene el mensaje
    @SerializedName("queue")
    public String queue;
    
    @SerializedName("routing_key")
    public String routing_key;

    // Por si el destinatario necesita saber de donde viene el mensaje
    @SerializedName("exchange")
    public String exchange;

    // El body del mensaje
    @SerializedName("message")
    public Object message;

    public static RabbitEvent fromJson(String json) {
        return GsonTools.gson().fromJson(json, RabbitEvent.class);
    }
}
