package com.edu.postest.model.entity

/**
 * Author: Meng
 * Date: 2025/11/21
 * Modify: 2025/11/21
 * Desc:
 */
class ChatEntity(msg: String?) {
    private var content: String? = msg
    private var role: String?
    private var id: String? = null

    init {
        this.role = "input"
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }

    fun getRole(): String? {
        return role
    }

    fun setRole(role: String?) {
        this.role = role
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }
}