package com.kudelich.mmfapp.data.mapper

import com.kudelich.mmfapp.data.local.department.DepartmentEntity
import com.kudelich.mmfapp.data.remote.dto.DepartmentDto
import com.kudelich.mmfapp.domain.model.Department

fun DepartmentDto.toDepartment(): Department {
    return Department(
        id = id,
        name = name
    )
}

fun DepartmentEntity.toDepartment(): Department {
    return Department(
        id = id,
        name = name
    )
}

fun Department.toDepartmentEntity(): DepartmentEntity {
    return DepartmentEntity(
        id = id,
        name = name
    )
}