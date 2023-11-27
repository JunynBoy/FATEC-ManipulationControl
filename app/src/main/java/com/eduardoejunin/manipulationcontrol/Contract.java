package com.eduardoejunin.manipulationcontrol;

import android.provider.BaseColumns;

public class Contract {
    public class Componente  implements BaseColumns {
        public static final String TABELA = "componente";
        public static final String COLUNA_NOME = "nome";
        public static final String COLUNA_Dosagem = "dosagem";
//        public static final String COLUNA_idPedido = "idPedido";
    }
    public class Pedido  implements BaseColumns {
        public static final String TABELA = "pedido";
        public static final String COLUNA_QUANTIDADE = "quantidade";
        public static final String COLUNA_Status = "status";
        public static final String COLUNA_Tamanho = "tamanho";
        public static final String COLUNA_idUsuario = "idUsuario";
        public static final String COLUNA_idCliente = "idCliente";
    }
    public class Usuario implements BaseColumns{
        public static final String TABELA = "usuario";
        public static final String COLUNA_nome = "nome";
        public static final String COLUNA_Funcao = "funcao";
        public static final String COLUNA_Login = "login";
        public static final String COLUNA_Senha = "senha";
    }
    public class Cliente implements BaseColumns{
        public static final String TABELA = "cliente";
        public static final String COLUNA_nome = "nome";
        public static final String COLUNA_Endereco = "endereco";
        public static final String COLUNA_Telefone = "telefone";
    }
}
