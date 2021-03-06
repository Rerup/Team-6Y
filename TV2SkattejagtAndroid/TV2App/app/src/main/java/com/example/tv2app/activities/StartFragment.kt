package com.example.tv2app.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tv2app.R
import com.example.tv2app.databinding.FragmentStartBinding
import com.example.tv2app.services.QRServiceRepository
import com.example.tv2app.viewmodels.QRServiceViewModel
import com.example.tv2app.viewmodels.TaskViewModel
import com.example.tv2app.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.integration.android.IntentIntegrator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * This is the first screen of the TV2 app. The user can choose between 3 buttons in a menu.
 */
class StartFragment : Fragment() {
    // Binding object instance with access to the views in the start_fragment.xml layout
    private lateinit var binding: FragmentStartBinding

    lateinit var auth : FirebaseAuth
    //ViewModels
    private val userViewModel: UserViewModel by sharedViewModel()
    private val taskViewModel : TaskViewModel by sharedViewModel()
    private val qrViewModel: QRServiceViewModel by sharedViewModel()






    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout XML file and return a binding object instance
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        auth = FirebaseAuth.getInstance()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Binding Elements Here
        val leaderboardButton = binding.leaderbord
        val hintButton = binding.hint
        val scanButton = binding.scanTreasure



        //OnClickListeners
        leaderboardButton.setOnClickListener{goToLeaderboard()}
        hintButton.setOnClickListener{goToHintScreen()}
        scanButton.setOnClickListener{qrScanner()}
    }


    private fun goToLeaderboard(){
        //findNavController().navigate(R.id.action_startFragment_to_leaderboardFragment)
        findNavController().navigate(R.id.action_startFragment_to_leaderboardActivity)

    }

    private fun goToHintScreen(){
        findNavController().navigate(R.id.action_startFragment_to_hintFragment)
    }


    //Pops up QR Scanner
    private fun qrScanner(){
        val integrator = IntentIntegrator.forSupportFragment(this@StartFragment)

        integrator.setOrientationLocked(false)
        integrator.setPrompt("Scan QR code")
        integrator.setBeepEnabled(false)
        //integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.initiateScan()
    }

    //
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {

            if (result.contents == null) {
                Toast.makeText(context, "Try again", Toast.LENGTH_LONG).show()
            }
            else {

                qrViewModel.saveQRContent(result.contents.toString())

                //Checking type of task and opens corresponding fragment
                when (qrViewModel.splitQRCode(qrViewModel.scannedQRContents)){
                    "Text" -> {
                        findNavController().navigate(R.id.action_startFragment_to_textTaskFragment)
                    }
                    "Quiz" -> {
                        findNavController().navigate(R.id.action_startFragment_to_quizTaskFragment)
                    }
                    else -> {
                        Toast.makeText(context, "No QR code found", Toast.LENGTH_LONG).show()
                    }

                }

                Toast.makeText(context, "Scanned : " + result.contents, Toast.LENGTH_LONG).show()
            }
        }
    }


}