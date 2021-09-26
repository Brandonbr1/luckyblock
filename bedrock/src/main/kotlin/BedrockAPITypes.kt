package mod.lucky.bedrock

external interface MCBlockPos {
    val x: Int
    val y: Int
    val z: Int
}

external interface MCVecPos {
    var x: Double
    var y: Double
    var z: Double
}

external interface MCBlock {
    val __identifier__: String;
}

external interface MCEntity {
    val pos: MCVecPos
}

external interface MCQuery {
    val query_id: Int
}


external interface MCTickingArea {}

external interface MCTickWorldComponent {
    val ticking_area: MCTickingArea
}
typealias MCWorld = MCTickWorldComponent

external interface MCNameableComponent {
    val name: String
}

external interface MCPlayerEntity {
}

external interface MCComponent<T> {
    var data: T
}

external interface MCEvent<T> {
    val data: T
}

external interface MCPlayerDestroyedBlockEvent {
    val block_identifier: String
    val block_position: MCBlockPos
    val player: MCPlayerEntity
}

external interface MCLoggerConfigEvent {
    var log_errors: Boolean
    var log_information: Boolean
    var log_warnings: Boolean
}

external interface MCChatEvent {
    var message: String
}

external interface MCServer {
    val level: Any
}

external interface MCCommandResult {
    val statusCode: Int
    val statusMessage: String
}

external interface MCServerSystem {
    var initialize: () -> Unit
    var log: (msg: Any?) -> Unit

    fun registerQuery(componentName: String, field1: String, field2: String, field3: String): MCQuery
    fun addFilterToQuery(query: MCQuery, componentName: String)
    fun getEntitiesFromQuery(query: MCQuery, field1Min: Int, field1Max: Int, field2Min: Int, field2Max: Int, field3Min: Int, field3Max: Int): Array<MCEntity>

    fun registerEventData(name: String, eventData: Any)
    fun <T> createEventData(name: String): MCEvent<T>
    fun broadcastEvent(name: String, eventData: Any)
    fun <T> listenForEvent(name: String, fn: (eventData: MCEvent<T>) -> Unit)

    fun registerComponent(name: String, dataSpec: Any)
    fun <T> createComponent(obj: Any, componentName: String): MCComponent<T>
    fun hasComponent(obj: Any, componentName: String): Boolean
    fun <T> getComponent(obj: Any, componentName: String): MCComponent<T>?
    fun <T> applyComponentChanges(obj: Any, component: MCComponent<T>)

    fun getBlock(tickingArea: MCTickingArea, blockPos: MCBlockPos): MCBlock

    fun createEntity(entityType: String, entityId: String): MCEntity?
    fun destroyEntity(entity: MCEntity)

    fun executeCommand(command: String, callback: (result: MCEvent<MCCommandResult>) -> Unit)
}
