package com.example.listasimples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val etnovatarefa = findViewById<EditText>(R.id.etnovatarefa)
            val btadd = findViewById<Button>(R.id.btadd)
            val lvtarefas = findViewById<ListView>(R.id.lvtarefas)

            //aqui criamos a lista de Strings, iniciqalmente vazia
            val listatarefas: ArrayList<String> = ArrayList()

            //p trabalhar com listas precisamo de um adaptador
            //um componentes adicional do android  p laypout de listas

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listatarefas)

            //aqui o adapter de listviewa recebe o adapter que criamos
            lvtarefas.adapter = adapter

            btadd.setOnClickListener {
                if (etnovatarefa.text.isNullOrBlank()) {
                    Toast.makeText(this, "digite uma tarefa ", Toast.LENGTH_SHORT).show()
                } else {
                    listatarefas.add(etnovatarefa.text.toString())
                    //notific ao adapter que tivemos alteracao nas listas
                    //notificado ele atiualiza os novoas elementos da lista na tela
                    adapter.notifyDataSetChanged()
                    etnovatarefa.setText("")
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
                        listatarefas.removeAt(position)
                        adapter.notifyDataSetChanged()
                        dialog.dismiss()        }
                        alerta.setNegativeButton("cancelar") { dialog, _ ->
                            dialog.dismiss() }
                            alerta.create().show()
                            true

                             }
                    }


                }


            }



