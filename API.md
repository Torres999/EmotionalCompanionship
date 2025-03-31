# 情感陪伴App API接口文档

## 文档说明

本文档描述了情感陪伴App客户端与服务端通信的API接口规范。所有接口均遵循REST风格，返回JSON格式数据。

## 接口基础信息

- **基础URL**: `https://api.emotionalcompanionship.com/v1`
- **接口调用说明**: 除了特别标明的接口外，大部分接口需要用户登录后才能调用
- **错误码说明**: 所有接口都可能返回以下错误码:
  - 200: 成功
  - 400: 请求参数错误
  - 401: 未授权（未登录或token失效）
  - 403: 权限不足
  - 404: 资源不存在
  - 500: 服务器内部错误

## 接口详情

### 1. 用户相关接口

#### 1.1 微信登录

```
POST /auth/login/wechat
```

**描述**: 使用微信授权登录，获取应用访问凭证

**请求参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| code | String | 是 | 微信授权返回的临时票据 |

**响应示例**:

```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "id": "wx_123456789",
      "userId": "888888",
      "name": "用户名",
      "description": "情感陪伴用户",
      "avatar": "https://example.com/avatar.jpg",
      "email": "user@example.com"
    }
  }
}
```

**备注**: 这个接口不需要登录即可访问。登录成功后需要在后续所有请求的header中携带token，格式为：`Authorization: Bearer {token}`

#### 1.2 获取用户信息

```
GET /user/profile
```

**描述**: 获取当前登录用户的详细信息

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": "wx_123456789",
    "userId": "888888",
    "name": "用户名",
    "description": "情感陪伴用户",
    "avatar": "https://example.com/avatar.jpg",
    "email": "user@example.com"
  }
}
```

### 2. 数字人相关接口

#### 2.1 获取数字人列表

```
GET /digital-humans
```

**描述**: 获取当前用户的所有数字人列表

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "获取成功",
  "data": [
    {
      "id": "1",
      "name": "女儿",
      "relation": "亲子",
      "personality": "温柔善解人意",
      "avatarUrl": "https://example.com/avatar1.jpg",
      "lastChatTime": "2025-03-24 10:30"
    },
    {
      "id": "2",
      "name": "儿子",
      "relation": "亲子",
      "personality": "聪明伶牙俐齿",
      "avatarUrl": "https://example.com/avatar2.jpg",
      "lastChatTime": "2025-03-23 18:15"
    },
    {
      "id": "3",
      "name": "小明",
      "relation": "好友",
      "personality": "温柔善解人意",
      "avatarUrl": "https://example.com/avatar3.jpg",
      "lastChatTime": "2025-03-22 12:40"
    }
  ]
}
```

#### 2.2 创建数字人

```
POST /digital-humans
```

**描述**: 创建一个新的数字人

**请求参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| name | String | 是 | 数字人称呼 |
| relation | String | 是 | 关系，可选值: "亲子"、"好友"、"其他" |
| personality | String | 是 | 性格特征，可选值: "温柔善解人意"、"聪明伶牙俐齿" |
| avatarUrl | String | 否 | 头像URL，可为空 |

**响应示例**:

```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": "4",
    "name": "新数字人",
    "relation": "亲子",
    "personality": "温柔善解人意",
    "avatarUrl": "",
    "lastChatTime": "2025-03-25 15:30"
  }
}
```

#### 2.3 获取数字人详情

```
GET /digital-humans/{id}
```

**描述**: 获取指定ID的数字人详情

**路径参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| id | String | 是 | 数字人ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": "1",
    "name": "女儿",
    "relation": "亲子",
    "personality": "温柔善解人意",
    "avatarUrl": "https://example.com/avatar1.jpg",
    "lastChatTime": "2025-03-24 10:30"
  }
}
```

#### 2.4 更新数字人信息

```
PUT /digital-humans/{id}
```

**描述**: 更新指定ID的数字人信息

**路径参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| id | String | 是 | 数字人ID |

**请求参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| name | String | 否 | 数字人称呼 |
| relation | String | 否 | 关系，可选值: "亲子"、"好友"、"其他" |
| personality | String | 否 | 性格特征，可选值: "温柔善解人意"、"聪明伶牙俐齿" |
| avatarUrl | String | 否 | 头像URL |

**响应示例**:

```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": "1",
    "name": "女儿(已更新)",
    "relation": "亲子",
    "personality": "聪明伶牙俐齿",
    "avatarUrl": "https://example.com/avatar1.jpg",
    "lastChatTime": "2025-03-24 10:30"
  }
}
```

#### 2.5 删除数字人

```
DELETE /digital-humans/{id}
```

**描述**: 删除指定ID的数字人

**路径参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| id | String | 是 | 数字人ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "删除成功",
  "data": null
}
```

### 3. 视频对话相关接口

#### 3.1 开始视频对话

```
POST /chat/start
```

**描述**: 开始与指定数字人的视频对话会话

**请求参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| digitalHumanId | String | 是 | 数字人ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "会话创建成功",
  "data": {
    "sessionId": "chat_123456789",
    "startTime": "2025-03-25 16:30:00",
    "webSocketUrl": "wss://api.emotionalcompanionship.com/ws/chat/1234"
  }
}
```

#### 3.2 结束视频对话

```
POST /chat/end
```

**描述**: 结束当前的视频对话会话

**请求参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| sessionId | String | 是 | 会话ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "会话已结束",
  "data": {
    "sessionId": "chat_123456789",
    "startTime": "2025-03-25 16:30:00",
    "endTime": "2025-03-25 16:45:30",
    "duration": 930 // 单位：秒
  }
}
```

### 4. 记忆相关接口

#### 4.1 获取记忆列表

```
GET /memories
```

**描述**: 获取所有保存的对话记忆

**请求参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| page | Integer | 否 | 页码，默认为1 |
| size | Integer | 否 | 每页大小，默认为20 |
| digitalHumanId | String | 否 | 数字人ID，可筛选特定数字人的记忆 |

**响应示例**:

```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "total": 5,
    "list": [
      {
        "id": "1",
        "title": "第一次视频对话",
        "content": "今天和妈妈进行了第一次视频对话，她看起来很开心...",
        "date": "2024-03-15 14:30",
        "imageUrl": null
      },
      {
        "id": "2",
        "title": "分享生活趣事",
        "content": "和妈妈分享了今天的工作和生活，她给了我很多建议...",
        "date": "2024-03-14 20:15",
        "imageUrl": "https://example.com/memory2.jpg"
      }
    ],
    "page": 1,
    "size": 20
  }
}
```

#### 4.2 保存对话记忆

```
POST /memories
```

**描述**: 保存一条新的对话记忆

**请求参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| title | String | 是 | 记忆标题 |
| content | String | 是 | 记忆内容 |
| digitalHumanId | String | 是 | 关联的数字人ID |
| imageUrl | String | 否 | 相关图片URL |

**响应示例**:

```json
{
  "code": 200,
  "message": "保存成功",
  "data": {
    "id": "6",
    "title": "新的对话记忆",
    "content": "今天和数字人聊了很多...",
    "date": "2025-03-25 17:00:00",
    "imageUrl": null
  }
}
```

#### 4.3 获取记忆详情

```
GET /memories/{id}
```

**描述**: 获取指定ID的记忆详情

**路径参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| id | String | 是 | 记忆ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": "1",
    "title": "第一次视频对话",
    "content": "今天和妈妈进行了第一次视频对话，她看起来很开心...",
    "date": "2024-03-15 14:30",
    "imageUrl": null,
    "digitalHuman": {
      "id": "1",
      "name": "女儿",
      "relation": "亲子"
    }
  }
}
```

#### 4.4 删除记忆

```
DELETE /memories/{id}
```

**描述**: 删除指定ID的记忆

**路径参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| id | String | 是 | 记忆ID |

**响应示例**:

```json
{
  "code": 200,
  "message": "删除成功",
  "data": null
}
```

## 数据模型

### 用户模型 (User)

```json
{
  "id": "String", // 用户唯一标识
  "userId": "String", // 用户展示ID
  "name": "String", // 用户名称
  "description": "String", // 用户描述
  "avatar": "String", // 头像URL
  "email": "String" // 邮箱
}
```

### 数字人模型 (DigitalHuman)

```json
{
  "id": "String", // 数字人唯一标识
  "name": "String", // 数字人称呼
  "relation": "String", // 关系，可选值: "亲子"、"好友"、"其他"
  "personality": "String", // 性格特征，可选值: "温柔善解人意"、"聪明伶牙俐齿"
  "avatarUrl": "String", // 头像URL
  "lastChatTime": "String" // 上次对话时间，格式：yyyy-MM-dd HH:mm
}
```

### 记忆模型 (MemoryItem)

```json
{
  "id": "String", // 记忆唯一标识
  "title": "String", // 记忆标题
  "content": "String", // 记忆内容
  "date": "String", // 记忆日期，格式：yyyy-MM-dd HH:mm
  "imageUrl": "String" // 图片URL，可为null
}
```

## 接口调用说明

### 访问控制
- 首页无需登录即可访问
- 创建数字人、开始视频对话、记忆库、我的页面需要登录后才能访问
- 未登录状态下点击相关功能会跳转到登录页

### 关系和性格特征选项
创建数字人时：
- 关系选项：亲子、好友、其他
- 性格特征选项：温柔善解人意、聪明伶牙俐齿

## 状态码

| 状态码 | 描述 |
| ----- | ---- |
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权（未登录或token失效）|
| 403 | 权限不足 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 | 