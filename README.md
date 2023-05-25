# TTC V2

TTC V2 is a Java-based tool that checks the availability of TikTok usernames by sending HTTP requests to the TikTok website. It utilizes multithreading to improve performance by checking multiple usernames concurrently.

## Features

- Checks the availability of TikTok usernames by sending HTTP requests.
- Utilizes multithreading to improve performance.
- Saves the available usernames to a separate file for further analysis.

## Getting Started

These instructions will guide you on how to set up and run TTC V2 on your local machine.

### Prerequisites

- Java Development Kit (JDK) 8 or above
- Git (optional)

### Installation

1. Clone the repository to your local machine:

   ```shell
   git clone https://github.com/your-username/ttc-v2.git
   ```
   
   If you don't have Git installed, you can download the repository as a ZIP file and extract it.
   
2. Change into the project directory:

   ```shell
    cd ttc-v2
   ```
### Customization

 - Thread Pool Size: In the checkUsernames method of the TTCV2 class, you can modify the line ExecutorService executor = Executors.newFixedThreadPool(10); to adjust the number of threads in the thread pool.

## License

This project is licensed under the MIT License. See the LICENSE file for details

## Disclaimer

This tool is for educational and research purposes only. Use it responsibly and respect TikTok's terms of service.
   
