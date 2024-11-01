## Functional Requirements Clarification / Assumptions
1. parking attendant?
2. multiple floors?
3. vehicle types (car, bike, truck, EVs)?
4. parking for specially abled?
5. payment modes?
6. parking rate (hourly, flat rate, discounts)?
7. surveillance?
8. display board for vacant spots per vehicle type?

---

## Classes, Attributes, and Methods

### 1. **ParkingLotSingleton** (Singleton Pattern using enum)
   - **Attributes**: 
     - `instance`
     - `List<ParkingFloor> floors`
     - `List<EntrancePanel> entrancePanels`
     - `List<ExitPanel> exitPanels`
     - `DisplayBoard displayBoard`
   - **Methods**: 
     - `getAvailableSpot(VehicleType vehicleType)`
     - `getFloors()`
     - `getEntrancePanels()`
     - `getExitPanels()`
     - `addEntrancePanel()`
     - `addExitPanel()`
     - `updateDisplay()`

### 2. **ParkingFloor**
   - **Attributes**: 
     - `String id`
     - `List<AbstractParkingSpot> spots`
     - `Map<VehicleType, Integer> availableSpotsCount;`
   - **Methods**:
     - `addSpot(AbstractParkingSpot spot)`
     - `updateSpotCount(VehicleType type, boolean increase)`
     - `getAvailableSpot(VehicleType vehicleType)`
     - `getAvailableSpotsCount()`

### 3. **AbstractParkingSpot** (Abstract Class)
   - **Attributes**: 
     - `String id`
     - `VehicleType vehicleType`
     - `boolean isAvailable`
     - `ParkingFloor floor`
   - **Methods**: 
     - `occupySpot()`
     - `freeSpot()`
     - `isAvailable()`
     - `getVehicleType()`
     - `getId()`

### 4. **Concrete Parking Spots** (Inherits from `ParkingSpot`)
   - **Classes**: 
     - `CarSpot`
     - `BikeSpot`
     - `TruckSpot`
     - `EvSpot`
     - `HandicapSpot` (only for car)
   - **Attributes**: Inherits properties from `ParkingSpot`

### 5. **ParkingSpotFactory** (Factory Pattern)
   - **Methods**: 
     - `createSpot(VehicleType type, String id, ParkingFloor floor)`

### 6. **Vehicle**
   - **Attributes**: 
     - `String licensePlate`
     - `VehicleType type`
   - **Methods**: 
     - `getType()`
     - `getLicensePlate()`

### 7. **Ticket**
   - **Attributes**: 
     - `Vehicle vehicle`
     - `ParkingSpot parkingSpot`
     - `LocalDateTime entryTime`
     - `LocalDateTime exitTime`
   - **Methods**: 
     - `getParkingSpot()`
     - `getEntryTime()`
     - `getExitTime()`
     - `getVehicle()`
     - `setParkingSpot()`
     - `setEntryTime()`
     - `setExitTime()`
     - `setVehicle()`

### 8. **TicketBuilder** (Builder Pattern)
   - **Attributes**: 
     - `Ticket ticket`
   - **Methods**: 
     - `getTicket()`
     - `withParkingSpot()`
     - `withEntryTime()`
     - `build()`

### 9. **EntrancePanel**
   - **Attributes**: 
     - `String id`
   - **Methods**: 
     - `generateTicket(Vehicle vehicle)` (through builder)

### 10. **ExitPanel**
   - **Attributes**: 
     - `String id`
   - **Methods**: 
     - `processPayment(Ticket ticket, PaymentMethod method, RateStrategyInterface rateStrategy)`

### 11. **PaymentTemplateMethod** (Template Method Pattern)
   - **Methods**: 
     - `processPayment()` (final)
        - `validateTicket()` (abstract)
        - `calculateAmount()` (abstract)
        - `makePayment()` (abstract)

### 12. **RateStrategyInterface** (Strategy Pattern)
   - **Methods**: 
     - `calculateParkingCharge(duration)`

### 13. **FlatRateStrategyImpl**
   - **Attributes**: 
    - `double flatRate`
   - **Methods**: 
    - `calculateParkingCharge(duration)`

### 14. **HourlyRateStrategyImpl**
   - **Attributes**: 
     - `double hourlyRate`
   - **Methods**: 
     - `calculateParkingCharge(duration)`

### 15. **ParkingRateManager**
   - **Attributes**: 
     - `RateStrategy rateStrategy`
   - **Methods**: 
     - `calculateParkingCharge(duration)`

### 16. **DisplayBoard** (Observer Pattern not implemented cuz KISS)
   - **Attributes**: 
     - `int availableCarSpots`
     - `int availableBikeSpots`
     - `int availableTruckSpots`
     - `int availableEVSpots`
   - **Methods**: 
     - `updateAvailableSpots()`
     - `display()`

---

## Enums

### 1. **VehicleType**
   - `CAR`
   - `BIKE`
   - `TRUCK`
   - `EV`

### 2. **PaymentMethod**
   - `CASH`
   - `CREDIT_CARD`
   - `DEBIT_CARD`
   - `DIGITAL_WALLET`

---

## Further Improvements
1. parking attendant
2. admin to add floors, adjust rates, etc.
3. advanced parking strategies (closest to entrance, exit, elevator - for this we should have multiple entrance, exit, elevator)
4. dynamic pricing (demand-supply, time of day, weekday/weekend/holiday, discounts - optional parameters hence through Builder Pattern)
5. mobile app integration
6. handling concurrency