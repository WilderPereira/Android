/*
 * Copyright (c) 2017 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.app.privacymonitor.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import com.duckduckgo.app.browser.R
import kotlinx.android.synthetic.main.item_privacy_practice.view.*

class PrivacyPracticesAdapter : RecyclerView.Adapter<PrivacyPracticesAdapter.PracticeViewHolder>() {

    companion object {
        const val GOOD = 1
        const val BAD = 2
    }

    private var terms: List<Pair<Int, String>> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PracticeViewHolder {
        val root = LayoutInflater.from(parent!!.context).inflate(R.layout.item_privacy_practice, parent, false)
        return PracticeViewHolder(root, root.icon, root.description)
    }

    override fun onBindViewHolder(holder: PracticeViewHolder, position: Int) {
        val term = terms[position]
        val descriptionResource = if (term.first == GOOD) R.string.practices_icon_content_good else R.string.practices_icon_content_bad
        holder.icon.contentDescription = holder.icon.context.getText(descriptionResource)
        holder.icon.setImageResource(if (term.first == GOOD) R.drawable.icon_success else R.drawable.icon_fail)
        holder.description.text = term.second.capitalize()
    }

    override fun getItemCount(): Int {
        return terms.size
    }

    fun updateData(goodTerms: List<String>, badTerms: List<String>) {
        this.terms = goodTerms.map { Pair(GOOD, it) } + badTerms.map { Pair(BAD, it) }
        notifyDataSetChanged()
    }

    class PracticeViewHolder(val root: View,
                             val icon: ImageView,
                             val description: TextView) : RecyclerView.ViewHolder(root)

}
