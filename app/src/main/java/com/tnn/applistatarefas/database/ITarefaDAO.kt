package com.tnn.applistatarefas.database

import com.tnn.applistatarefas.model.Tarefa

interface ITarefaDAO {
    fun salvar( tarefa: Tarefa ): Boolean
    fun atualizar( tarefa: Tarefa ): Boolean
    fun deletar( id: Int ): Boolean
    fun listar(): List<Tarefa>
}