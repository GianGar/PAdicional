document.write('<nav id="sidebar">
        <div class="sidebar-header">
        <div class="container text-center">
            <h3><a href="#" th:href="@{/adicional/entes/salir}">Policía Adicional</a></h3></div>
        </div>

        <ul class="list-unstyled components">
            <li>
                <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                <i class="fas fa-desktop"></i> Gestión</a>
                <ul class="collapse list-unstyled" id="homeSubmenu">
                    <li>
                        <a href="#" th:href="@{/adicional/entes/index}"><i class="fas fa-building"></i> Entidades y objetivos</a>
                        </li><li>
                        <a href="#"><i class="fas fa-tasks"></i> Asignar servicios</a>
                    </li>
                    <li>
                        <a href="#" th:href="@{/sgp/feriados/mostrarferiados}"><i class="fas fa-calendar-alt"></i> Feriados</a>
                    </li>
                    <li>
                		<a href="#"><i class="fas fa-cog"></i> Generar servicios</a>
            		</li>
                </ul>
            </li>
            
            <li>
                <a href="#paginaListado" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                <i class="fas fa-file-alt"></i> Listados</a>
                <ul class="collapse list-unstyled" id="paginaListado">
                    <li>
                        <a href="#"><i class="fas fa-file-alt"></i> Listado 1</a>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-file-alt"></i> Listado 2</a>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-file-alt"></i> Listado 3</a>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-file-alt"></i> Listado 4</a>
                    </li>
                </ul>
            </li>
            
            <li>
                <a href="#paginaInforme" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                <i class="fas fa-chart-pie"></i> Estadísticas</a>
                <ul class="collapse list-unstyled" id="paginaInforme">
                    <li>
                        <a href="#"><i class="fas fa-chart-pie"></i> Estadística 1</a>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-chart-pie"></i> Estadística 2</a>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-chart-pie"></i> Estadística 3</a>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-chart-pie"></i> Estadística 4</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">
                <i class="fas fa-wrench"></i> Configuración</a>
                <ul class="collapse list-unstyled" id="pageSubmenu">
                    <li>
                        <a href="#"><i class="fas fa-table"></i> Categorías de entidades</a>
                    </li>
                    <li>
                        <a href="#"><i class="fas fa-home"></i> Destinos</a>
                    </li>
                </ul>
            </li>
        </ul>
        <ul class="list-unstyled CTAs">
            <li>
                <a href="#" th:href="@{/login?logout}" class="download"><i class="fas fa-power-off"></i> CERRAR SESIÓN</a>
            </li>
        </ul>

    </nav>
);