package br.com.food.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    private static final long serialVerisonUID = 1l;

    private Date data;
    private String mensagem;
    private String detalhes;

    public ExceptionResponse(Date data, String mensagem, String detalhes) {
        this.data = data;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    public Date getData() {
        return data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }
}
