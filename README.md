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

| subproject                               | description                                                                                              |
|------------------------------------------|----------------------------------------------------------------------------------------------------------|
| [misty-utils](misty-utils)               | 來自另一個 repo 用 git submodule 引入, 為我長年工作累積的工具包, 僅相依 `slf4j-api` 的 java 原生專案                                 |
| [order-customer](order-customer)         | 用來模擬客戶送出買賣訂單的系統, 可用於做系統模擬                                                                                |
| [order-matcher-api](order-matcher-api)   | 定義 **訂單匹配系統**  相關的操作介面                                                                                   |
| [order-matcher-core](order-matcher-core) | 實作 **訂單匹配系統**  的操作介面                                                                                     |
| [order-portal-api](order-portal-api)     | 定義 **訂單入口系統**  相關的操作介面, 簡單來說就是接受客戶買賣訂單的入口, 可以是http/websocket/gRPC等來源                                     |
| [order-portal-http](order-portal-http)   | 實作 **訂單入口系統**  的操作介面, 該實作是從http接受訂單                                                                      |
| [order-scanner-api](order-scanner-api)   | 定義 **訂單掃描系統**  相關的操作介面                                                                                   |
| [order-scanner-core](order-scanner-api)  | 實作 **訂單掃描系統**  的操作介面                                                                                     |
| [order-service](order-service)           | **訂單服務整合系統** 將 `order-matcher` `order-portal` `order-scanner` 整合在一起執行, 可在流量不大的時候整合所有系統啟動, 而不用一開始就分散系統浪費錢 |
| [order-test](order-test)                 | 基本上就是一個整合測試環境, 除了相依三個核心邏輯模組外, 還相依了 `order-customer` 可直接跑完整的系統測試                                          |
| [order-utils](order-utils)               | 跟訂單匹配系統核心邏輯無關的一些工具, 採用 spring 3 autoconfig 載入機制                                                          |

- 採用 java9 JPMS 隔離專案存取權限, 以更嚴謹的機制方便以後抽換實作
- 使用 `ServiceLoader` 來載入實作, 用以解耦所有實作
- RDB 採用 **讀寫分離** 維持系統的高響應
- 訂單進來寫入的 table 為 **異步複製 (Asynchronous Replication)** 同步策略, 主庫寫入後立即返回, 後續由從庫同步 (影響使用者送出訂單的響應速度)
- 買賣成立寫入的 table 為 **半同步複製 (Semi-Synchronous Replication)** 同步策略, 主庫寫入後與至少一個從庫同步後返回 (影響 `order-matcher` 與 RDB
  的響應速度)
- 上述資料寫入 RDB 後, 皆會直接在 redis 寫一份 cache 並給定 TTL, 因為使用者習慣會是送出請求後馬上再刷新查詢是否訂單有成立, 這樣做可以消除資料未同步造成
  client 的疑慮, 又可以維持高響應
- 核心的專案可以獨立運作, 也可以被整合在一起執行, 也可以 scale out/in, 達到系統的拓展性
 

