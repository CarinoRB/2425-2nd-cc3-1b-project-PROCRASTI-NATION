- **Model**: Data classes representing business entities.
- **View**: CLI-based interface to display data and interact with the user.
- **ViewModel**: Acts as the bridge between the View and Model, handling data processing and user input.

---

# Development Plan for Car Rental System (MVVM Structure - CLI)

## 1. Architecture Overview

### MVVM Components

- **Model**: Represents the business entities and data classes, such as `Car`, `User`, and `RentalOrder`.
- **View**: The command-line interface (CLI) that interacts with the user, displaying information and taking input.
- **ViewModel**: Contains the logic that manipulates data from the Model and prepares it for display in the View. It also manages user input from the View.

### Directory Structure

```
src/
├── models/          # Data models representing business entities (e.g., Car, User)
├── views/           # CLI components for user interaction (e.g., prompts, menus)
├── viewmodels/      # Logic for connecting the View and Model, processing data for the View
```

## 2. DEV Phases

### Phase 1: Core Infrastructure

1. **Basic Setup**
   - Create the basic structure of the app
   - Set up CLI for displaying prompts and accepting user input
   - Integrate unit testing framework

2. **User Login System**
   - **Model**: 
     - Create a `User` class with properties such as `username`, `password`, and `session_token`.
   - **View**: 
     - Create CLI prompts for logging in (e.g., `Enter username:`, `Enter password:`).
   - **ViewModel**: 
     - Implement logic to handle user authentication by verifying credentials against stored data and managing the session.

3. **Main Data Storage**
   - **Model**: 
     - Define classes for `Car`, `RentalOrder`, and `User` with attributes like `car_id`, `availability`, `user_id`, `rental_dates`.
     - Set up in-memory data storage or connect to a database for persistent storage.
   - **ViewModel**: 
     - Implement methods to retrieve car data, manage rental orders, and handle user actions.

### Phase 2: Customer Features

1. **Car Browsing**
   - **Model**: 
     - Expand `Car` model to include details like `car_type`, `price_per_day`, and `availability`.
   - **View**: 
     - Display available cars with information such as car name, type, and price.
     - Allow users to search or filter cars (e.g., by type or price).
   - **ViewModel**: 
     - Implement methods for searching and filtering cars based on user input.
     - Retrieve available cars and display them in the View.

2. **Booking System**
   - **Model**: 
     - Create a `RentalOrder` class to represent booking details, such as `pickup_date`, `return_date`, `total_price`, and `status`.
   - **View**: 
     - Prompt users to enter booking details like location, dates, and selected car.
   - **ViewModel**: 
     - Implement booking logic to calculate the total price based on the car and rental duration.
     - Handle creation and updating of `RentalOrder` instances.

3. **Payment Processing**
   - **Model**: 
     - Extend `RentalOrder` with payment status and payment method attributes.
   - **View**: 
     - Display payment details and options for users to confirm the booking.
   - **ViewModel**: 
     - Implement payment handling logic, including price calculation and updating order status.

### Phase 3: Order Management

1. **Rental History**
   - **Model**: 
     - Extend `RentalOrder` to track past rentals and status.
   - **View**: 
     - Display a list of past rentals and rental details.
   - **ViewModel**: 
     - Implement methods to retrieve and display rental history for the logged-in user.

2. **Changing Orders**
   - **Model**: 
     - Modify `RentalOrder` to support changes such as cancellations or updates to booking details.
   - **View**: 
     - Display options to update or cancel existing bookings.
   - **ViewModel**: 
     - Implement logic to update orders or process cancellations, including refund management.

### Phase 4: Admin Features

1. **User Management**
   - **Model**: 
     - Extend the `User` class to include roles and permissions (e.g., `admin`, `customer`).
   - **View**: 
     - Display user management prompts (e.g., adding, editing, and deleting users).
   - **ViewModel**: 
     - Implement user management logic, including role-based actions.

2. **Car Inventory Management**
   - **Model**: 
     - Expand `Car` model to include maintenance tracking and availability.
   - **View**: 
     - Provide CLI prompts for managing car inventory (e.g., adding, editing, or removing cars).
   - **ViewModel**: 
     - Implement inventory management logic, including car maintenance and availability updates.

3. **Order Management for Admins**
   - **Model**: 
     - Extend `RentalOrder` to include order statuses (e.g., `pending`, `confirmed`, `completed`).
   - **View**: 
     - Display rental order management options, such as updating order statuses.
   - **ViewModel**: 
     - Implement logic to manage and update rental order statuses.

### Phase 5: Testing and Finishing

1. **Testing**
   - **Unit Tests**: 
     - Test individual Model, ViewModel, and View components.
   - **Integration Tests**: 
     - Test how Models, ViewModels, and Views work together.
   - **User Acceptance Tests**: 
     - Ensure the system functions as expected for end users.

2. **Improvements**
   - **Performance**: 
     - Optimize the app for better performance.
   - **Bug Fixes**: 
     - Address any identified issues during testing.

3. **Documentation**
   - **Developer Guides**: 
     - Provide documentation on how to extend or modify the system.
   - **User Guides**: 
     - Create user manuals for customers and admins.
   - **Maintenance**: 
     - Write documentation on how to maintain the app.

## 3. Success Criteria

- All planned features are implemented and working correctly.
- The app is responsive and easy to navigate using the CLI.
- Tests are comprehensive and ensure reliability.
- Documentation is complete and accurate.
- User feedback is positive and aligns with expected functionality.

## 4. Next Steps

1. Set up development tools and environment.
2. Build the foundational components: Models, ViewModels, and Views.
3. Start implementing the core features, starting with user authentication and car browsing.
4. Continue with the development of booking, payment, and order management functionalities.
5. Test each feature thoroughly and refine as needed.

---
