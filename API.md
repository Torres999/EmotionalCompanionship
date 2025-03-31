# 情感陪伴App API接口文档

## 文档说明

本文档描述了情感陪伴App客户端与服务端通信的API接口规范。所有接口均遵循REST风格，返回JSON格式数据。

## 接口基础信息

- **基础URL**: `https://api.emotionalcompanionship.com/v1`
- **接口调用说明**: 除了特别标明的接口外，大部分接口需要用户登录后才能调用
- **认证方式**: 需要认证的接口在请求头（headers）中添加 `Authorization: Bearer {token}` 来进行认证
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

**备注**: 这个接口不需要登录即可访问。登录成功后，应用会将token保存在本地，之后所有需要认证的接口都通过请求头中的Authorization字段携带此token。

#### 1.2 获取用户信息

```
GET /user/profile
```

**描述**: 获取当前登录用户的详细信息

**请求参数**: 无

**认证要求**: 是

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

#### 1.3 检查登录状态

```
GET /auth/check
```

**描述**: 检查当前用户的登录状态是否有效

**请求参数**: 无

**响应示例**:

```json
{
  "code": 200,
  "message": "登录状态有效",
  "data": {
    "isLoggedIn": true,
    "userId": "wx_123456789"
  }
}
```

#### 1.4 获取详细个人信息

```
GET /user/details
```

**描述**: 获取用户的详细个人信息（点击"我的"页面头像时调用）

**请求参数**: 无

**认证要求**: 是

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
    "email": "user@example.com",
    "registerDate": "2025-01-15",
    "vipLevel": 1,
    "vipExpireDate": "2025-12-31",
    "balance": 100.00,
    "totalDigitalHumans": 3,
    "totalChatMinutes": 120
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

**认证要求**: 是

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

**认证要求**: 是

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

**认证要求**: 是

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

**认证要求**: 是

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

**认证要求**: 是

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

**认证要求**: 是

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

**认证要求**: 是

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

**认证要求**: 是

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

**认证要求**: 是

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

**认证要求**: 是

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

**认证要求**: 是

**响应示例**:

```json
{
  "code": 200,
  "message": "删除成功",
  "data": null
}
```

### 5. 充值相关接口

#### 5.1 获取充值套餐列表

```
GET /payment/packages
```

**描述**: 获取可用的充值套餐列表

**请求参数**: 无

**认证要求**: 是

**响应示例**:

```json
{
  "code": 200,
  "message": "获取成功",
  "data": [
    {
      "id": "1",
      "name": "月卡",
      "description": "30天无限视频对话",
      "price": 30.00,
      "originalPrice": 40.00,
      "duration": 30,
      "benefits": ["无限视频对话", "优先客服支持"]
    },
    {
      "id": "2", 
      "name": "季卡",
      "description": "90天无限视频对话",
      "price": 80.00,
      "originalPrice": 120.00,
      "duration": 90,
      "benefits": ["无限视频对话", "优先客服支持", "专属头像框"]
    },
    {
      "id": "3",
      "name": "年卡",
      "description": "365天无限视频对话",
      "price": 298.00,
      "originalPrice": 480.00,
      "duration": 365,
      "benefits": ["无限视频对话", "优先客服支持", "专属头像框", "生日提醒"]
    }
  ]
}
```

#### 5.2 创建充值订单

```
POST /payment/orders
```

**描述**: 创建充值订单

**请求参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| packageId | String | 是 | 充值套餐ID |
| paymentMethod | String | 是 | 支付方式，可选值："wechat"(微信支付)、"alipay"(支付宝) |

**认证要求**: 是

**响应示例**:

```json
{
  "code": 200,
  "message": "订单创建成功",
  "data": {
    "orderId": "order_123456789",
    "amount": 30.00,
    "packageName": "月卡",
    "createTime": "2025-03-25 18:30:00",
    "expiryTime": "2025-03-25 18:45:00", // 订单有效期
    "paymentParams": {
      "appId": "wx123456789",
      "timeStamp": "1585847525",
      "nonceStr": "random_string",
      "package": "prepay_id=wx123456789",
      "signType": "MD5",
      "paySign": "sign_string"
    }
  }
}
```

#### 5.3 获取充值记录

```
GET /payment/records
```

**描述**: 获取用户的充值记录

**请求参数**:

| 参数名 | 类型 | 是否必须 | 描述 |
| ----- | ---- | ------- | ---- |
| page | Integer | 否 | 页码，默认为1 |
| size | Integer | 否 | 每页大小，默认为20 |

**认证要求**: 是

**响应示例**:

```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "total": 3,
    "list": [
      {
        "id": "record_1",
        "orderId": "order_123456789",
        "packageName": "月卡",
        "amount": 30.00,
        "paymentMethod": "微信支付",
        "status": "支付成功",
        "paymentTime": "2025-03-25 18:35:20",
        "validityPeriod": "2025-03-25 至 2025-04-24"
      },
      {
        "id": "record_2",
        "orderId": "order_987654321",
        "packageName": "季卡",
        "amount": 80.00,
        "paymentMethod": "支付宝",
        "status": "支付成功",
        "paymentTime": "2025-02-15 10:20:30",
        "validityPeriod": "2025-02-15 至 2025-05-15"
      },
      {
        "id": "record_3",
        "orderId": "order_111222333",
        "packageName": "月卡",
        "amount": 30.00,
        "paymentMethod": "微信支付",
        "status": "支付失败",
        "paymentTime": "2025-01-05 14:25:10",
        "validityPeriod": ""
      }
    ],
    "page": 1,
    "size": 20
  }
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
  "email": "String", // 邮箱
  "registerDate": "String", // 注册日期
  "vipLevel": "Integer", // VIP等级
  "vipExpireDate": "String", // VIP到期日期
  "balance": "Double" // 账户余额
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

### 充值套餐模型 (Package)

```json
{
  "id": "String", // 套餐唯一标识
  "name": "String", // 套餐名称
  "description": "String", // 套餐描述
  "price": "Double", // 当前价格
  "originalPrice": "Double", // 原价
  "duration": "Integer", // 有效期(天)
  "benefits": ["String"] // 套餐权益列表
}
```

### 充值记录模型 (PaymentRecord)

```json
{
  "id": "String", // 记录唯一标识
  "orderId": "String", // 订单ID
  "packageName": "String", // 套餐名称
  "amount": "Double", // 支付金额
  "paymentMethod": "String", // 支付方式
  "status": "String", // 支付状态
  "paymentTime": "String", // 支付时间
  "validityPeriod": "String" // 有效期
}
```

## 接口调用说明

### 客户端登录态管理
- 客户端通过微信登录获取token后，将token存储在本地
- 所有需要登录态的功能，客户端通过检查本地是否存在有效token来判断用户是否已登录
- 若本地无token或token已过期，则提示用户登录
- 登录成功后，服务端返回的token会被客户端保存在本地，用于后续的接口调用

### 访问控制
- 首页无需登录即可访问
- 点击"首页"中"创建新的数字人"、"开始视频对话"时，客户端会检查本地是否存在token，未登录时提示跳转至登录页面
- 点击"记忆库"底部导航栏按钮时，客户端会检查本地是否存在token，未登录时跳转至登录页面
- "我的"页面中"关于我们"、"隐私保护"以外的页面，客户端会检查本地是否存在token，未登录时跳转至登录页面

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