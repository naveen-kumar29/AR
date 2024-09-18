package com.example.agumentedrealitysampleproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.ar.core.Config
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.node.ModelNode

class MainActivity : AppCompatActivity() {
    lateinit var sceneView: ArSceneView
    lateinit var placeButton:ExtendedFloatingActionButton
    lateinit var singleSofaButton:ExtendedFloatingActionButton
    lateinit var teaTableButton:ExtendedFloatingActionButton
    lateinit var modelNode: ArModelNode

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sceneView=findViewById(R.id.sceneView)
        placeButton=findViewById(R.id.sofa)
        /*singleSofaButton=findViewById(R.id.single_sofa)
        teaTableButton=findViewById(R.id.tea_table)*/

        placeButton.setOnClickListener {
            placeModel()
        }

        Handler().postDelayed(Runnable {
            modelNode=ArModelNode().apply {
                Log.e("TAG", "onCreate: ")
                loadModelGlbAsync(
//                    glbFileLocation = "models/house.glb",
//                    glbFileLocation = "models/stylish_loft_in_milan.glb",
                    glbFileLocation = "models/home_landed.glb",
                    autoAnimate=true,
                    scaleToUnits=10f
                )
                {
                    sceneView.planeRenderer.isVisible=true
                }
                onAnchorChanged={
                    placeButton.isGone
                }
                    placementMode= ArModelNode.DEFAULT_PLACEMENT_MODE
            }
            sceneView.addChild(modelNode)
        }, 500)

    }

    private fun placeModel(){

        modelNode?.anchor()
        sceneView.planeRenderer.isVisible=false
        placeButton.isVisible=false
    }
}