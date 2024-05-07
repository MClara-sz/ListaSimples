package com.example.listasimples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val etnovatarefa = findViewById<EditText>(R.id.etnovatarefa)
            val btadd = findViewById<Button>(R.id.btadd)
            val tvtitulo = findViewById<TextView>(R.id.tvtitulo)
            val lvtarefas = findViewById<ListView>(R.id.lvtarefas)

            val fab = findViewById<FloatingActionButton>(R.id.fab)
            fab.setOnClickListener {
                tvtitulo.isVisible = false
                etnovatarefa.isVisible = true
                etnovatarefa.isEnabled = true
                btadd.isVisible = true
            }

            val listaTarefas: ArrayList<String> = ArrayList()
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTarefas)
            lvtarefas.adapter = adapter


            btadd.setOnClickListener {
                if (etnovatarefa.text.isNullOrEmpty()) {
                    Toast.makeText(this, "digite uma tarefa ", Toast.LENGTH_SHORT).show()
                } else {
                    listaTarefas.add(etnovatarefa.text.toString())
                    //notific ao adapter que tivemos alteracao nas listas
                    //notificado ele atiualiza os novoas elementos da lista na tela
                    adapter.notifyDataSetChanged()
                    etnovatarefa.setText("")
                    etnovatarefa.isVisible = false
                    etnovatarefa.isEnabled = false
                    btadd.isVisible = false
                    tvtitulo.isVisible = true
                }
            }
                //listener para eventos de clique longi em algum item de lista
                //passamos para a funcao de callback e pposicao de item clicado
                lvtarefas.setOnItemLongClickListener { _, _, position, _ ->
                    //aqui montamos a caixa de dialogos
                    val alerta = AlertDialog.Builder(this)
                    alerta.setTitle("atencao")
                    alerta.setMessage("quer msm excluir esse item?")
                    alerta.setPositiveButton("confirmar") {dialog, _ ->

                        //case obotao confirmar seja clicado , remover o item da lista
                        listaTarefas.removeAt(position)
                        adapter.notifyDataSetChanged()
                        dialog.dismiss()        }
                        alerta.setNegativeButton("cancelar") { dialog, _ ->
                            dialog.dismiss() }
                            alerta.create().show()
                            true

                             }
                    }


                }






