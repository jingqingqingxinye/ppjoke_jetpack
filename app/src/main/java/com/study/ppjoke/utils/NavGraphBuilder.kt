package com.study.ppjoke.utils

import android.content.ComponentName
import androidx.fragment.app.FragmentActivity
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphNavigator
import androidx.navigation.fragment.FragmentNavigator
import com.study.ppjoke.model.FixFragmentNavigator

/**
 *
 * @author jingqingqing
 * @version
 */
object NavGraphBuilder {
    fun build(controller: NavController, activity: FragmentActivity, containerId: Int) {
        val provider = controller.navigatorProvider
        val fragmentNavigator: FragmentNavigator = provider.getNavigator(FragmentNavigator::class.java)
//        val fragmentNavigator = FixFragmentNavigator(activity, activity.supportFragmentManager, containerId)
//        provider.addNavigator(fragmentNavigator)
        val activityNavigator: ActivityNavigator = provider.getNavigator(ActivityNavigator::class.java)
        val navGraph = NavGraph(NavGraphNavigator(provider))
        val destConfig = AppConfig.getDestConfig() ?: return
        for (value in destConfig.values) {
            if (value.isFragment) {
                val destination = fragmentNavigator.createDestination()
                destination.className = value.className
                destination.id = value.id
                destination.addDeepLink(value.pageUrl)

                navGraph.addDestination(destination)
            } else {
                val destination = activityNavigator.createDestination()
                destination.id = value.id
                destination.addDeepLink(value.pageUrl)
                destination.setComponentName(ComponentName(AppGlobals.getApplication()!!.packageName, value.className))

                navGraph.addDestination(destination)
            }

            if (value.asStarter) {
                navGraph.startDestination = value.id
            }
        }
        controller.graph = navGraph
    }
}