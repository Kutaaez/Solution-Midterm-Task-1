# 1. Smart Home Control System (Advanced Midterm Project)

## Project Description
This project implements a smart home control system that allows you to control various devices such as lights, thermostats, and security cameras. The project uses various design patterns to demonstrate efficient design and modularity of the system.

## Design Patterns Used
- **Composite Pattern**: Devices are organized in a hierarchical structure, where devices can be either individual elements or groups of devices.
- **Decorator Pattern**: Devices can be modified to add new features without changing their source code.
- **Abstract Factory Pattern**: Devices of different types are created through factories, which allows for compatibility and extensibility.
- **Facade Pattern**: Simplifies the use of the system by providing a common interface for all devices.
- **Adapter Pattern**: Provides integration with legacy or third-party systems by adapting their interfaces.

## Project Structure
- **SmartHomeController** — Facade for managing all devices in the smart home. Uses the Facade pattern.
- **Device and DeviceGroup** — Basic interfaces and classes for representing devices and their groups (e.g., rooms or floors). Uses the Composite pattern.
- **Decorators** — Decorators for adding functionality to devices. Uses the Decorator pattern.
- **Factories** — Factory classes for creating different types of devices (basic and advanced). Uses the Factory pattern.
- **Adapters** — Classes for adapting external or legacy systems. Uses the Adapter pattern.
# Smart Home Control System (Advanced Midterm Project)

## Project Description
This project implements a smart home control system that allows you to control various devices such as lights, thermostats, and security cameras. The project uses various design patterns to demonstrate efficient design and modularity of the system.

## Design Patterns Used
- **Composite Pattern**: Devices are organized in a hierarchical structure, where devices can be either individual elements or groups of devices.
- **Decorator Pattern**: Devices can be modified to add new features without changing their source code.
- **Abstract Factory Pattern**: Devices of different types are created through factories, which allows for compatibility and extensibility.
- **Facade Pattern**: Simplifies the use of the system by providing a common interface for all devices.
- **Adapter Pattern**: Provides integration with legacy or third-party systems by adapting their interfaces.

## Project Structure
- **SmartHomeController** — Facade for managing all devices in the smart home. Uses the Facade pattern.
- **Device and DeviceGroup** — Basic interfaces and classes for representing devices and their groups (e.g., rooms or floors). Uses the Composite pattern.
- **Decorators** — Decorators for adding functionality to devices. Uses the Decorator pattern.
- **Factories** — Factory classes for creating different types of devices (basic and advanced). Uses the Factory pattern.
- **Adapters** — Classes for adapting external or legacy systems. Uses the Adapter pattern.

- ![idea64_qPx4tXARBD](https://github.com/user-attachments/assets/e6d3eded-bd01-408f-8174-0316b2486173)

- 

# 2. Online Payment Gateway 

## Project Description
This project implements an Online Payment Gateway that supports multiple payment methods including credit card, PayPal, and cryptocurrency. The system processes basic transactions and integrates with an external API for payment verification and processing. The project utilizes design patterns to ensure flexibility, scalability, and maintainability.

## Design Patterns Used
- **Factory Method Pattern**: Encapsulates the creation of different payment method objects (e.g., CreditCardPayment, PayPalPayment, CryptoPayment) and allows for easy extension of new payment methods.
- **Adapter Pattern**: Integrates with an external or mock API, ensuring that external systems (like payment services) conform to the internal system's interface.

## Features Implemented
- **Multiple Payment Methods**: Supports credit card, PayPal, and cryptocurrency payments.
- **Transaction Management**: Handles transaction statuses (pending, completed, failed) and provides transaction history.
- **Error Handling**: Includes exception handling for insufficient funds and invalid transactions.
- **External API Integration**: Connects to an external API for payment verification (credit cards, PayPal, and cryptocurrency).

### Payment Methods
1. **Credit Card**: Users can process payments via registered credit cards. The system verifies if the card is registered and checks for sufficient balance.
2. **PayPal**: Users can make payments using PayPal accounts. The system checks if the account is registered and verifies the available balance.
3. **Crypto**: Users can make payments using cryptocurrency wallets. The system ensures sufficient funds before processing the payment.

### External API Integration
- Integrated with an external payment API for credit card, PayPal, and cryptocurrency payment processing. The system interacts with the external API to verify balances and process payments.

### Transaction and Logging
- **Transaction**: The system keeps track of the status of each payment (pending, completed, failed).
- **TransactionLogger**: Logs transaction details for audit and debugging purposes.

## Project Structure
- **Adapters**: Contains the `ExternalPaymentAPI` class, which interacts with the external payment service to validate and process transactions.
- **Exceptions**: Defines custom exceptions (`InsufficientFundsException`, `TransactionException`, `PaymentException`) to handle error conditions.
- **Payment Methods**: Classes for each payment method (Credit Card, PayPal, Crypto), implementing the payment interface.
- **Factory**: A single factory class that creates and returns the appropriate payment method object based on user input.
- **TransactionLogger**: Logs transaction details and provides an audit trail for each payment processed.
![idea64_9MBu0xgUSE](https://github.com/user-attachments/assets/624eeeb1-3db8-4546-a09e-bdcb865bfe3e)
![idea64_77njuinCnq](https://github.com/user-attachments/assets/84396ac4-1a40-43c1-b566-683e6e889802)

# 3. Coffee Shop Simulator (Easy – 40 points)

## Project Description
This Coffee Ordering System allows users to customize their coffee orders by choosing a base coffee type and adding various ingredients or toppings. The system uses the **Factory Method** and **Decorator** design patterns to efficiently manage coffee types and dynamic customization.

## Design Patterns Used
- **Factory Method Pattern**: 
  - A `CoffeeFactory` that creates different coffee objects (e.g., Espresso, Cappuccino, Latte, Rough Coffee).
  - Each coffee type implements a common interface to define methods like `getCost()` and `getDescription()`.

- **Decorator Pattern**: 
  - Decorators are used to dynamically add extra ingredients or toppings (milk, caramel syrup, whipped cream, chocolate syrup) to coffee drinks.
  - Each decorator wraps the base coffee object, modifying its cost and description.

## Coffee Prices & Descriptions

- **Espresso** – $3.00  
  "A strong and rich coffee shot with an intense flavor."

- **Cappuccino** – $4.00  
  "A perfect balance of espresso, steamed milk, and frothy milk foam."

- **Latte** – $4.50  
  "Smooth and creamy coffee with a generous amount of steamed milk."

- **Rough Coffee** – $2.50  
  "A bold and unfiltered coffee with a strong, raw taste."

## Add-ons Prices & Descriptions

- **Milk** – +$0.50  
  "Adds a smooth and creamy texture to your coffee."

- **Caramel Syrup** – +$0.70  
  "A sweet caramel touch for a richer flavor."

- **Chocolate Syrup** – +$0.80  
  "A delightful chocolatey twist to enhance your drink."

- **Whipped Cream** – +$0.60  
  "A light and fluffy topping for a sweeter, creamier taste."

## Requirements Breakdown
1. **Factory Method Pattern**: 
   - A `CoffeeFactory` creates instances of different coffee types based on the user’s selection.
   - Each coffee type implements a common `Coffee` interface with methods such as `getCost()` and `getDescription()`.

2. **Decorator Pattern**: 
   - Decorators allow users to add toppings to their coffee dynamically.
   - Each topping (e.g., Milk, Caramel Syrup) is represented as a decorator class that modifies the `getCost()` and `getDescription()` methods.

## How It Works

1. **Select a Coffee**: 
   - Users can select a base coffee type (Espresso, Cappuccino, Latte, or Rough Coffee) from the factory.
  
2. **Add Toppings**: 
   - Users can choose from several toppings or ingredients (Milk, Caramel Syrup, Chocolate Syrup, Whipped Cream) that are applied dynamically using the decorator pattern.

3. **Get Final Cost and Description**: 
   - The system calculates the total cost of the customized coffee, including the base coffee and added toppings.
   - A description of the final coffee is provided, including both the base coffee and any toppings.

## Demo Application
The Coffee Shop Simulator provides a console-based or lightweight GUI where users can:
- Select a coffee base (Espresso, Cappuccino, Latte, Rough Coffee).
- Add multiple toppings (Milk, Caramel Syrup, Chocolate Syrup, Whipped Cream).
- View the final cost and description of the customized coffee order.

![idea64_4p0TbrmFKx](https://github.com/user-attachments/assets/33380149-1094-4ebd-86f7-79ea3df8c085)


![idea64_9kYazduJ6e](https://github.com/user-attachments/assets/235ee395-79b8-4ad9-83ac-a34ba71814ca)

![idea64_LQG5SHV8PJ](https://github.com/user-attachments/assets/e218cdc8-20a4-49be-83ae-ac7058221f71)
