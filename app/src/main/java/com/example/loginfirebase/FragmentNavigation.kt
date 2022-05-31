package com.example.loginfirebase

import androidx.fragment.app.Fragment


//interface used to navigate fragments with the support of the above library
interface FragmentNavigation {
    fun navigateFrag(fragment: Fragment, addToStack: Boolean)
}