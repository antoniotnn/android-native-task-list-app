package com.tnn.applistatarefas.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.tnn.applistatarefas.database.DatabaseHelper
import com.tnn.applistatarefas.model.Tarefa

class TarefaDAO(context: Context) : ITarefaDAO {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(tarefa: Tarefa): Boolean {

        val conteudos = ContentValues()
        conteudos.put(DatabaseHelper.DESCRICAO, tarefa.descricao)

        try {
            escrita.insert(
                DatabaseHelper.TABELA_TAREFAS,
                null,
                conteudos
            )
            Log.i("info_db", "Sucesso ao salvar tarefa")
        } catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao salvar tarefa")
            return false
        }
        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        val args = arrayOf(tarefa.idTarefa.toString())
        val contentValues = ContentValues()
        contentValues.put(DatabaseHelper.DESCRICAO, tarefa.descricao)

        try {
            escrita.update(
                DatabaseHelper.TABELA_TAREFAS,
                contentValues,
                "${DatabaseHelper.ID_TAREFA} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao atualizar tarefa")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Erro ao atualizar tarefa")
            return false
        }
        return true
    }

    override fun deletar(id: Int): Boolean {
        val args = arrayOf(id.toString())
        try {
            escrita.delete(
                DatabaseHelper.TABELA_TAREFAS,
                "${DatabaseHelper.ID_TAREFA} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao remover tarefa")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Erro ao salvar tarefa")
            return false
        }
        return true
        try {
            escrita.delete(
                DatabaseHelper.TABELA_TAREFAS,
                "${DatabaseHelper.ID_TAREFA} = ?",
                args
            )
            Log.i("info_db", "Sucesso ao remover tarefa")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_db", "Erro ao salvar tarefa")
            return false
        }
        return true
    }

    override fun listar(): List<Tarefa> {

        val listaTarefas = mutableListOf<Tarefa>()

        val sql = "SELECT " +
                    "${DatabaseHelper.ID_TAREFA}, " +
                    "${DatabaseHelper.DESCRICAO}, " +
                     "strftime('%d/%m/%Y %H:%M', ${DatabaseHelper.DATA_CRIACAO}) data_criacao" +
                " FROM ${DatabaseHelper.TABELA_TAREFAS}"

        val cursor = leitura.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex(DatabaseHelper.ID_TAREFA)
        val indiceDescricao = cursor.getColumnIndex(DatabaseHelper.DESCRICAO)
        val indiceDataCriacao = cursor.getColumnIndex(DatabaseHelper.DATA_CRIACAO)

        while (cursor.moveToNext()) {
            val idTarefa = cursor.getInt(indiceId)
            val descricao = cursor.getString(indiceDescricao)
            val dataCriacao = cursor.getString(indiceDataCriacao)

            listaTarefas.add(
                Tarefa(idTarefa, descricao, dataCriacao)
            )
        }

        return listaTarefas
    }

}