package com.adsamcik.recycler.adapter.implementation.multitype

/**
 * Multi type adapter that builds upon [BaseMultiTypeAdapter] and adds safer method to register types.
 * with enums.
 */
open class MultiTypeAdapter<DataTypeEnum : Enum<*>, Data : MultiTypeData<DataTypeEnum>, ViewHolder : MultiTypeViewHolder<Data>>
	: BaseMultiTypeAdapter<Data, ViewHolder>() {

	/**
	 * Registers [MultiTypeViewHolderCreator] for given [DataTypeEnum].
	 * Provides additional type safety and error reporting compared to [registerType]
	 * with integer type value.
	 *
	 * @param typeValue Type of [Data] the [creator] creates view holder for
	 * @param creator View holder creator used for creating views for data of type [typeValue]
	 *
	 * @throws AlreadyRegisteredException Thrown when type was previously registered
	 */
	@Throws(AlreadyRegisteredException::class)
	fun registerType(
			typeValue: DataTypeEnum,
			creator: MultiTypeViewHolderCreator<Data, ViewHolder>
	) {
		try {
			registerType(typeValue.ordinal, creator)
		} catch (e: AlreadyRegisteredException) {
			throw AlreadyRegisteredException("Type $typeValue already registered", e)
		}
	}
}

