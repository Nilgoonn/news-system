# News System

A lightweight Java-based real-time news processing system that simulates a streaming news feed, analyzes sentiment, and reports top positive news based on priority within a sliding time window.

---

## 📌 Overview

This project consists of two main applications:

### 1. Feed Application
Generates mock news items and sends them over a TCP socket.

### 2. Analyzer Application
Receives news items, processes them, filters positive headlines, and reports top news in real-time.

---

## 🏗 Architecture

The system is composed of the following modules:

- **Mock News Feed**
    - Generates random news headlines and priorities
    - Sends data to server via socket

- **News Server**
    - Accepts incoming TCP connections
    - Delegates each client to a handler thread

- **Client Handler**
    - Reads messages line-by-line from socket
    - Sends data to `NewsProcessor`

- **News Processor**
    - Parses incoming messages
    - Applies sentiment analysis
    - Stores only positive news items with timestamps

- **Sentiment Analyzer**
    - Uses a simple keyword-based approach
    - Considers a headline positive if more than half of its words are in a predefined positive word set

- **News Reporter**
    - Runs every 10 seconds
    - Retrieves last 10 seconds of news
    - Logs total count of positive news
    - Logs top 3 highest priority news items

---

## ⚙️ Configuration

Configuration is located in:
