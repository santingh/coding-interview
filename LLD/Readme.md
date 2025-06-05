```markdown
# Low-Level Design (LLD) Practice Questions

---

## 1. LRU Cache (Thread‐Safe)
**What to build:**  
An in‐memory cache with `get(key)` and `put(key, value)` operations that evicts the least‐recently‐used entry when capacity is exceeded.

**Key Focus Areas:**
- Doubly linked list + hash‐map structure for O(1) access and eviction.
- Implementing thread‐safety (e.g., using `ReentrantLock` or `ReadWriteLock`).
- Ensuring O(1) time complexity for both `get` and `put`.

---

## 2. Parking Lot System
**What to build:**  
Model a parking garage with multiple levels and spots of different sizes (e.g., motorbike, compact, large). Implement `park(vehicle)` and `unpark(ticket)` APIs.

**Key Focus Areas:**
- Class hierarchy for `Vehicle`, `Spot`, `ParkingLevel`, `Ticket`.
- Applying the Strategy Pattern (assigning different spot‐allocation strategies).
- Thread‐safe operations: multiple cars entering/leaving concurrently (e.g., `synchronized` blocks or `ReentrantLock`).

---

## 3. Multi‐Level Rate Limiter
**What to build:**  
A rate limiter that supports both per‐user and global request quotas. Expose `allowRequest(userId)` which returns true/false based on token availability.

**Key Focus Areas:**
- Token Bucket vs. Leaky Bucket implementations.
- Concurrency control: updating counters from multiple threads safely.
- Designing for extensibility (e.g., adding per‐API or per‐region limits later).

---

## 4. Chat Room / Messaging Service
**What to build:**  
Real‐time chat where users can join “rooms” and broadcast messages to all participants. Provide operations like `sendMessage(userId, roomId, message)` and `joinRoom(userId, roomId)`.

**Key Focus Areas:**
- Observer/Publish‐Subscribe pattern for distributing messages.
- Managing concurrent message delivery (thread pools, `ExecutorService`).
- Clean interfaces for adding new transports (e.g., WebSocket vs. HTTP fallback).

---

## 5. In‐Memory File Storage (Like Dropbox/Google Drive)
**What to build:**  
A simple file‐storage engine supporting `uploadFile(userId, fileMetadata, InputStream)`, `shareFile(userId, fileId, targetUserId)`, and `downloadFile(userId, fileId)`.

**Key Focus Areas:**
- Designing `File`, `Folder`, `Permission` classes (Composite Pattern for folder hierarchy).
- Thread‐safe file upload/download (handling partial writes, resumable uploads).
- Scalability: abstract storage backend so you can swap from local disk to S3.

---

## 6. Library Management System
**What to build:**  
Manage books, members, and check‐in/check‐out workflows. APIs include `checkoutBook(userId, bookId)`, `returnBook(userId, bookId)`, `searchBooks(query)`.

**Key Focus Areas:**
- Class design for `Book`, `User`, `Catalog`, `LoanRecord`.
- Applying Repository pattern for data access (in‐memory vs. database).
- Enforcing consistency when multiple users try to checkout the same book (locking per‐book, optimistic locking).

---

## 7. Elevator Control System
**What to build:**  
Simulate N elevators serving M floors. Users press “Up”/“Down” on floors or select “Floor” inside the elevator. The system decides which elevator responds.

**Key Focus Areas:**
- Strategy for request‐assignment (e.g., nearest car, load‐balancing).
- State machine for each `Elevator` (IDLE, MOVING_UP, MOVING_DOWN, DOOR_OPEN).
- Thread‐safety: multiple request threads arriving simultaneously, coordinating via `ConcurrentLinkedQueue` or `BlockingQueue`.

---

## 8. Online Auction/Bidding System
**What to build:**  
Allow sellers to create auctions, and buyers to place bids. Expose `createAuction(itemId, startTime, endTime)`, `placeBid(auctionId, userId, bidAmount)`, and `closeAuction(auctionId)`.

**Key Focus Areas:**
- Class hierarchy: `Auction`, `Bid`, `Item`, `User`.
- Ensuring atomicity of `placeBid` (no two bids overwrite each other: use `synchronized` or a distributed lock simulation).
- Handling auction expiration (e.g., `ScheduledExecutorService` to trigger `closeAuction`).

---

## 9. Distributed ID Generator (e.g., Snowflake)
**What to build:**  
Generate unique 64‐bit IDs with a time component, machine ID component, and sequence number.

**Key Focus Areas:**
- Bit‐partitioning of ID (timestamp, datacenterId, workerId, sequence).
- Thread‐safe counter for `sequence` (e.g., using `AtomicLong`).
- Handling clock skew or backwards time movement gracefully.

---

## 10. Notification Service (Email/SMS) Queue
**What to build:**  
A service that accepts notification requests and dispatches them (e.g., via email or SMS). Provide `enqueueNotification(notification)`, worker threads pick up notifications, send, and retry on failure.

**Key Focus Areas:**
- Producer/Consumer pattern (e.g., `BlockingQueue<Notification>`).
- Designing retry logic with exponential backoff.
- Abstraction over transport (`EmailSender`, `SmsSender` with a common interface).

---

## 11. Thread Pool Implementation
**What to build:**  
A basic fixed‐size thread pool supporting `submit(Task)`, `shutdown()`, and `awaitTermination()`.

**Key Focus Areas:**
- Worker threads continuously pulling from a `BlockingQueue<Runnable>`.
- Graceful shutdown vs. forceful shutdown semantics.
- Handling rejected tasks when the queue is full.

---

## 12. News Feed/Timeline Service (e.g., Twitter Feed)
**What to build:**  
Each user sees a personalized timeline combining posts from people they follow. Provide `postTweet(userId, content)` and `getTimeline(userId, limit)`.

**Key Focus Areas:**
- Data structures: per‐user feed cache vs. fan‐out on write vs. fan‐out on read.
- Modeling `User`, `Tweet`, `FollowerGraph` classes.
- Concurrency: new tweets arriving while timelines are being fetched.

---

## 13. Simple File Lock Service (Distributed Lock Simulator)
**What to build:**  
A centralized lock manager that allows clients to `acquireLock(resourceId, clientId)` and `releaseLock(resourceId, clientId)`.

**Key Focus Areas:**
- Tracking lock ownership in a `ConcurrentHashMap<resourceId, LockInfo>`.
- Handling reentrancy (same client acquiring multiple times) via a counter.
- Timeout/lease mechanism (locks expire if not renewed).

---

## 14. URL Shortener (e.g., bit.ly)
**What to build:**  
Convert a long URL to a short code, store the mapping, and redirect. APIs: `shortenUrl(longUrl)`, `getOriginalUrl(shortCode)`.

**Key Focus Areas:**
- Generating unique short codes (incremental counter + base62 encoding or hash + collision handling).
- Designing a `UrlRepository` interface so you can swap out in‐memory vs. database storage.
- Caching popular URLs with an eviction policy (e.g., LRU).

---

## 15. Metrics Aggregator / StatsD‐Like Service
**What to build:**  
Clients send metrics (`counter`, `gauge`, `timer`). The service aggregates and periodically flushes to storage. Provide `incrementCounter(name)`, `recordTiming(name, value)`, and a background thread to flush.

**Key Focus Areas:**
- Designing `Metric`, `CounterMetric`, `TimerMetric` classes (use Inheritance + Open/Closed Principle).
- Thread‐safe increments (e.g., using `AtomicLong`, `ConcurrentHashMap`).
- Scheduling periodic flush with `ScheduledExecutorService`.

---

## 16. Logging Library (Like Log4j)
**What to build:**  
A configurable logging framework that supports multiple log levels (e.g., DEBUG, INFO, WARN, ERROR), appenders (console, file, rolling file), and formatting. Expose APIs like `Logger.getLogger(name)`, `logger.debug(msg)`, `logger.error(msg, throwable)`.

**Key Focus Areas:**
- **Designing Logger Hierarchy:** Class diagram for `Logger`, `Appender` (ConsoleAppender, FileAppender, RollingFileAppender), `Layout`/`Formatter`. Use the Factory or Builder Pattern to create configurable `Logger` instances.
- **Configurable Levels & Filters:** Implement a `LogLevel` enum and enable runtime configuration (e.g., via XML/JSON/Java API) to set levels per package or class.
- **Thread Safety:** Ensure that multiple threads can log concurrently without corrupting shared buffers. Use a thread‐safe queue (e.g., `BlockingQueue<LogEvent>`) for asynchronous logging, and a dedicated dispatcher thread to flush events to appenders.
- **Pluggable Appenders & Layouts:** Program to interfaces (e.g., `Appender`, `Layout`) so new appenders or formats can be added without modifying core code (Open/Closed Principle).
- **Performance Considerations:** Employ Lazy Initialization of appenders, minimize synchronization in the hot path (e.g., use lock‐free structures or `ReentrantReadWriteLock` for config changes), and support buffering or batching of writes.

---

## 17. LeetCode Code Runner Service
**What to build:**  
A service that accepts user‐submitted code (Java, Python, C++, etc.) and executes it against a predefined set of test cases, returning pass/fail and error output. Expose `submitCode(userId, problemId, sourceCode, language)` and `getResult(submissionId)`.

**Key Focus Areas:**
- **Isolation & Sandboxing:** Design a `CodeExecutor` component that spawns each submission in a containerized or isolated thread/process (e.g., use Java’s `ProcessBuilder` to invoke a restricted Docker container). Enforce resource limits (CPU time, memory).
- **Plugin Architecture for Multiple Languages:** Use a Strategy or Command Pattern to encapsulate language‐specific compilation and execution steps (`JavaExecutor`, `PythonExecutor`, `CppExecutor`). Program to the `LanguageExecutor` interface.
- **Test Case Management:** Model `TestCase` as objects with input, expected output, time/memory limits. Store them in a `Repository` or in-memory cache for fast lookup.
- **Asynchronous Processing & Queueing:** Use a Producer/Consumer pattern: incoming submissions are placed into a `BlockingQueue<Submission>`. Worker threads poll the queue, execute code, and write results to a shared `ResultStore` (e.g., `ConcurrentHashMap<submissionId, Result>`).
- **Concurrency & Scalability:** Design a thread pool (custom `ThreadPoolExecutor`) that can scale based on load. Implement backpressure: when the queue is full, reject or throttle submissions.
- **Security & Timeouts:** Enforce timeouts for each execution (e.g., use `Future.get(timeout)`) and handle infinite loops or malicious code gracefully.
- **Reporting & Metrics:** Track per‐language throughput, success rates, and average execution time. Consider a metrics aggregator to periodically flush these to persistent storage.

---

## 18. Snake and Ladder Game (Multiplayer)
**What to build:**  
A turn‐based board game simulator where 2–6 players roll dice and move their tokens through a board with numbered squares, including snakes (move down) and ladders (move up). Expose `startGame(playerList)`, `rollDice(playerId)`, and `getGameState()`.

**Key Focus Areas:**
- **Class Design:**
  - `Game`: Orchestrates turns, tracks `currentPlayer`, and board state.
  - `Board`: Holds a mapping of squares to `Entity` (e.g., `Snake(start, end)`, `Ladder(start, end)`).
  - `Player`: Maintains `playerId`, `position`, and `state` (active/finished).
  - `Dice`: Encapsulates rolling logic (e.g., random 1–6).
- **SOLID Principles:**
  - **Single Responsibility:** Separate `Game` logic from UI/CLI (e.g., printing moves).
  - **Open/Closed:** Allow new board entities (e.g., “BoostPad” or “Trap”) by having a `BoardEntity` interface.
- **Concurrency (Optional for Real‐Time Play):** If multiple clients are connected (e.g., via WebSocket), wrap shared `Game` state in thread‐safe structures or synchronize on turn transitions to prevent race conditions.
- **Turn Management & State Machine:** Implement a simple state machine for each turn: `ROLL_DICE → MOVE_TOKEN → CHECK_ENTITY → NEXT_PLAYER`.
- **Persistence & Replay (Extension):** Abstract `GamePersistence` interface to save moves in case of server crash or for a “replay game” feature.

---

## 19. Sublime Text–Like IDE (Core Components)
**What to build:**  
The core backend of a text editor/IDE supporting multiple open files, syntax highlighting, plugins, and a command palette. Provide APIs like `openFile(path)`, `saveFile(buffer)`, `executeCommand(commandName)`.

**Key Focus Areas:**
- **Buffer & Document Model:**
  - `Buffer` class holds the text (e.g., a **Gap Buffer** or **Rope** data structure) for efficient insert/delete.
  - `Document` wraps `Buffer` and metadata (file path, dirty flag).
- **Syntax Highlighting:**
  - Use the Visitor Pattern or Lexer/Parser pipeline: tokenize input via a `SyntaxHighlighter` interface; each language plugin implements its own highlighter.
  - Keep highlighting incremental: on text change, re‐highlight only the affected region.
- **Tab & Window Management:**
  - `Window` class manages a list of `EditorView` instances.
  - `EditorView` binds a `Document` to a UI component and tracks cursor position, selection.
- **Plugin System:**
  - Define a `Plugin` interface with lifecycle methods (`onLoad()`, `onEvent(event)`).
  - Use a Registry or ServiceLoader pattern to discover plugins at runtime (e.g., read from a “packages/” directory).
- **Command Palette / Key Binding:**
  - Model `Command` as objects with `execute()`; maintain a `Map<String, Command>` for quick lookup.
  - Allow remapping of keys: store `KeyBinding` objects (key chord → command name) in a `Configuration` component.
- **Concurrency (Background Tasks):**
  - For tasks like “Find in Files,” spawn background threads using a `ThreadPoolExecutor` and report progress back to the UI via a Publish/Subscribe or Observer pattern.
  - Ensure buffer modifications are synchronized so background tasks don’t read stale or inconsistent text.
- **Undo/Redo:**
  - Implement the Command Pattern or Memento Pattern: each edit action is stored as an `EditCommand` with `undo()`/`redo()`. Maintain an `UndoManager` to push/pop on stacks.
- **Trade‐Offs & Scalability:**
  - **Memory vs. Speed:** A full parse tree for each file (for advanced features like error squiggles) uses more memory versus on‐the‐fly tokenization.
  - **Plugin Isolation:** Decide between running plugins in the same process (faster, but riskier) versus separate processes (safer, but higher IPC overhead).

---

## Tips for Each Problem
- **Start with Interfaces & Core Classes:**  
  Begin by sketching the main entities and their relationships (draw a quick UML high‐level class diagram). Identify the core interfaces and abstract classes first, then flesh out concrete implementations.

- **Apply SOLID/OOPS Principles:**  
  - **Single Responsibility Principle (SRP):** Each class should have one clear purpose. For example, separate storage logic from business logic.  
  - **Open/Closed Principle (OCP):** Program to interfaces or abstract classes so new functionality (e.g., a new `LanguageExecutor` or a new `Appender`) can be added without modifying existing code.  
  - **Liskov Substitution Principle (LSP):** Subclasses should be replaceable for their base types without altering correctness (e.g., a `ConsoleAppender` should behave like any other `Appender`).  
  - **Interface Segregation Principle (ISP):** Keep interfaces small; clients should not be forced to implement methods they don’t use.  
  - **Dependency Inversion Principle (DIP):** High‐level modules should not depend on low‐level modules. Instead, both should depend on abstractions (e.g., programming to a `LockManager` interface rather than a concrete `ReentrantLock`).

- **Consider Concurrency & Thread‐Safety Early:**  
  - Identify what shared state must be protected.  
  - Decide between coarse‐grained locks (e.g., synchronizing entire methods) versus fine‐grained locks (`ReentrantLock`, `ReadWriteLock`, or `Atomic*` classes).  
  - Use thread‐safe collections (`ConcurrentHashMap`, `BlockingQueue`, `ConcurrentLinkedQueue`) where appropriate.  
  - For producer/consumer patterns (e.g., Logging Library, Code Runner, Notification Service), use a `BlockingQueue` plus dedicated worker threads or a `ThreadPoolExecutor`.

- **Think Through Edge Cases:**  
  - What happens when capacity is full (e.g., LRU Cache, URL Shortener)?  
  - How do you handle simultaneous updates to the same resource (e.g., Auction Bidding, Library Checkout)?  
  - How does the system behave on shutdown or restart (e.g., Thread Pool, File Storage, IDE)?  
  - What if external dependencies fail (e.g., disk I/O errors in Logging Library, container crashes in Code Runner)?

- **Discuss Trade‐Offs & Scalability:**  
  - Be prepared to articulate why you chose a particular data structure or pattern (e.g., Gap Buffer vs. Rope in the IDE).  
  - Consider how you’d scale out components (e.g., sharding a cache by key range, distributing auction servers by item category, partitioning user feeds in the News Feed service).  
  - Identify bottlenecks and single points of failure. Suggest improvements like replication, partitioning, or caching layers.

- **Draw Sequence Flows for Critical Paths:**  
  In a live interview, quickly sketch how key operations flow through your classes. For example:  
  - In the Parking Lot system, show how `park(vehicle)` navigates from the `ParkingLotManager` to a `SpotAllocationStrategy` to actual `Spot` assignment.  
  - In the Code Runner, show how `submitCode(...)` enqueues a submission, how a worker thread picks it up, compiles, executes in a sandbox, and returns results.

- **Write a Minimal Java Implementation:**  
  - Start with interfaces (e.g., `Cache<K,V>`, `RateLimiter`, `FileExecutor`), then a basic in‐memory implementation.  
  - Add concurrency support (locks, thread pools, concurrent collections).  
  - Include basic unit tests (e.g., JUnit) for correctness under concurrent scenarios (multiple threads logging, simultaneous bids, concurrent file uploads).

- **Be Prepared to Extend & Evolve:**  
  - Interviews often ask: “How would you evolve this design if requirements change?”  
  - For example, if the LRU Cache needs persistence to disk, how would you modify your classes? If the Logging Library needs to support remote log aggregation, where would you insert that functionality?

---

Happy practicing! Focus on writing clear, modular Java code that adheres to OOPS/SOLID principles, handles concurrency robustly, and demonstrates your understanding of real‐world trade‐offs.
```
