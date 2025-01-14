package com.tnn.applistatarefas.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tarefa(
    var idTarefa: Int,
    var descricao: String,
    var dataCadastro: String
) : Parcelable