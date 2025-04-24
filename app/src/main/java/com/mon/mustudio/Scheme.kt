package com.mon.mustudio

/**
 * Author: Meng
 * Date: 2025/04/24
 * Modify: 2025/04/24
 * Desc:
 * 自定义 Scheme：通过 <data> 标签中的 android:scheme 属性定义了自定义的 URI 协议（如 http、https 或 app）。
 * Host 和 PathPrefix：通过 android:host 和 android:pathPrefix 限制了匹配的 URI 范围。
 * Intent-Filter：允许应用通过特定的 URI 被其他应用调用，并支持深度链接。
 *
 * Scheme 是 Android 中用于处理 URI 的类
 * <intent-filter android:autoVerify="true">
 *     <action android:name="android.intent.action.VIEW" />
 *     <category android:name="android.intent.category.DEFAULT" />
 *     <category android:name="android.intent.category.BROWSABLE" />
 *     <data android:scheme="app" />
 *     <data android:host="mustudio.com" />
 *     <data android:pathPrefix="/" />
 * </intent-filter>
 *
 * Scheme 是一个用于处理 URI 的类
 * // 获取 Intent 中的 URI
 *     val data = intent?.data
 *     data?.let {
 *         val scheme = it.scheme // 获取 scheme，例如 "app"
 *         val host = it.host // 获取 host，例如 "mustudio.com"
 *         val path = it.path // 获取路径，例如 "/example"
 *         val query = it.query // 获取查询参数，例如 "id=123&name=test"
 *
 *         // 解析参数
 *         val id = it.getQueryParameter("id") // 获取 id 参数
 *         val name = it.getQueryParameter("name") // 获取 name 参数
 *
 *         // 打印参数
 *         println("Scheme: $scheme, Host: $host, Path: $path, Query: $query, ID: $id, Name: $name")
 *     }
 */
class Scheme {
}