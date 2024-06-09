# aery-practice-order

about order matching system

---

Imagine you have a trade engine that accepts orders via the protocol (or triggering)
you defined. An order request at least has this information (buy or sell, quantity,
market price or limit price).
The engine matches buy and sell orders that have the same price. Orders have the
same price determined by their timestamp (FIFO). Pending orders queue up in your
system until they are filled or killed. Your system will have multiple traders executing
orders at the same time.

## What is expected?

- SOLID design accommodates requirements to change in the future.
- Testable, compilable and runnable code.
- Concurrent and thread-safe considered.
- Document your design which can be any human-readable form. For example,
  README file.
- A git repository can be accessed publicly.
- Golang are preferred but not required.

---

project structure:

| subproject                         | description                                                                                          |
|------------------------------------|------------------------------------------------------------------------------------------------------|
| [misty-utils](misty-utils)         | 來自另一個 repo 用 git submodule 引入, 為我長年工作累積的工具包, 僅相依 `slf4j-api` 的 java 原生專案                             |
| [order-api](./order-api)           | 定義可操作訂單匹配系統的介面                                                                                       |
| [order-core](./order-core)         | 主要核心程式碼                                                                                              |
| [order-customer](./order-customer) | 用來模擬客戶送出買賣訂單的系統                                                                                      |
| [order-portal](./order-portal)     | 整合測試入口, 可以用來啟動多個 [order-core](./order-core) (模擬分散式架構) 與 一個 [order-customer](./order-customer) 模擬系統運作 |
| [order-utils](./order-utils)       | 跟訂單匹配系統核心邏輯無關的一些工具, 採用 spring 3 autoconfig 載入, 如果需要抽換 [order-core](./order-core) 則可以沿用               |

- 採用 java9 JPMS 隔離專案存取權限
- 使用 `ServiceLoader` 來載入實作, 解耦 `order-portal` 與實作, 方便以後抽換實作
- RDB 採用 **讀寫分離**, 維持系統的高響應
- 訂單一進來寫入的 table 為 **異步複製 (Asynchronous Replication)** 同步策略, 主庫寫入後立即返回, 後續由從庫同步 (影響使用者送出訂單的響應速度)
- 買賣成立寫入的 table 為 **半同步複製 (Semi-Synchronous Replication)** 同步策略, 主庫寫入後與至少一個從庫同步後返回 (影響 `OrderMacher` 與 RDB
  的響應速度)
- 上述資料寫入 RDB 後, 皆會直接在 redis 寫一份 cache 並給定 TTL, 因為使用者習慣會是送出請求後馬上再刷新查詢是否訂單有成立, 這樣做可以消除資料未同步造成
  client 疑慮, 又可以維持高響應
 

